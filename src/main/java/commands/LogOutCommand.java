package commands;

import manager.PagesJsp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class LogOutCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of LogOutCommand");
        request.getSession().invalidate();
        return PagesJsp.getInstance().getProperty(PagesJsp.INDEX);
    }
}
