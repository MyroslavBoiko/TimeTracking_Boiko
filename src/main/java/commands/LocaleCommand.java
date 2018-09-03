package commands;

import manager.PagesJsp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class LocaleCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of LocaleCommand");
        request.getSession().setAttribute("lang", request.getParameter("localeLang"));
        String page = request.getParameter("pageParam");
        if (page == null || page.equals("")) {
            return PagesJsp.getInstance().getProperty(PagesJsp.INDEX);
        } else {
            return page;
        }

    }
}
