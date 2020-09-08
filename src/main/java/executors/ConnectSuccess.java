package executors;

@FunctionalInterface
public interface ConnectSuccess {
    /**
     * Called when a connection is established to AMQP Server.
     */
    void handle(Void aVoid);
}
