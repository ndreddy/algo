package threads;

import javax.servlet.*;
import java.util.concurrent.atomic.AtomicLong;

//@ThreadSafe
public class CountingServlet implements Servlet {
    private final AtomicLong count = new AtomicLong(0);
    public long getCount() { return count.get(); }

    public void service(ServletRequest req, ServletResponse resp) {

        count.incrementAndGet();

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }



    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}


