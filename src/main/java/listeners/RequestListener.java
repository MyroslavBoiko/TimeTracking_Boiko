package listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

    private static final Logger LOGGER = Logger.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String uri = "Request Destroyed for " + req.getRequestURI();
        String id = "Request Destroyed with ID="+ req.getRequestedSessionId();
        LOGGER.info(uri + ":" + id);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String uri = "Request Initialized for " + req.getRequestURI();
        String id = "Request Initialized with ID="+ req.getRequestedSessionId();
        LOGGER.info(uri + ":" + id);
    }
}
