package commands.header;

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
public class AdminPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of AdminPageCommand");
        String page = PagesJsp.getInstance().getProperty(PagesJsp.ADMIN);
        request.setAttribute("currentPage", page);
        return page;
    }
}
