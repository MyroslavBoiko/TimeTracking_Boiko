package commands.sidebar.client;

import commands.Command;
import manager.PagesJsp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class SetTimeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SetTimeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of SetTimeCommand");
        String page;
        String[] temp = request.getParameter("assignment").split(":", 2);
        String description = temp[0].trim();
        request.setAttribute("description", description);
        page = PagesJsp.getInstance().getProperty(PagesJsp.SET_TIME);
        request.setAttribute("currentPage", page);
        return page;
    }
}
