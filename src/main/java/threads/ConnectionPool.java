package threads;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

interface Connection {
    int read() throws IOException; // reads an int from the connection
    void close() throws IOException; // closes the connection
}

class StreamConnection implements Connection {
    private final InputStream input = null;
    public int read() throws IOException { return input.read(); }
    public void close() throws IOException { input.close(); }
}

class StreamConnectionPool {
    List<StreamConnection> freeConnections;  //openSomeConnectionsSomehow();

    public StreamConnectionPool(int bound) {
        freeConnections = new ArrayList<>(bound);
        for (int i = 0; i < bound; i++) {
            freeConnections.add(new StreamConnection());
        }
    }


    StreamConnection borrowConnection(){
        if (freeConnections.isEmpty()) throw new IllegalStateException("No free connections");
        return freeConnections.remove(0);
    }
    void returnConnection(StreamConnection conn){
        freeConnections.add(conn);
    }
}

public class ConnectionPool {

    private final StreamConnectionPool streamPool;   // ...;

    public ConnectionPool(int bound) {
        streamPool = new StreamConnectionPool(bound);
    }

    Connection getConnection() {
        final StreamConnection realConnection = streamPool.borrowConnection();
        return new Connection(){
            private boolean closed = false;
            public int read() throws IOException {
                if (closed) throw new IllegalStateException("Connection closed");
                return realConnection.read();
            }
            public void close() {
                if (!closed) {
                    closed = true;
                    streamPool.returnConnection(realConnection);
                }
            }
            protected void finalize() throws Throwable {
                try {
                    close();
                } finally {
                    super.finalize();
                }
            }
        };
    }

}