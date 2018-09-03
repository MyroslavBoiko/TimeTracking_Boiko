package listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Mirosha
 */

public class SessionListener implements HttpSessionAttributeListener {

    private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        LOGGER.info("Add: " + event.getClass().getSimpleName() + " : "+ event.getName()
                + " : " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        LOGGER.info("Remove: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        LOGGER.info("Replace: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

}
