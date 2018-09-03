package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import entities.User;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mirosha
 */
public class ShowUsersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of ShowUsersCommand");
        String page;
        final int recordsPerPage = 5;
        UsersService service = ServiceFactory.getUsersService();
        Paginator paginator = new Paginator(service.getCountOfRows(), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<User> users = service.getUsersPerPage(paginator.getCurrentPage(), recordsPerPage);
        request.setAttribute("users", users);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        page = PagesJsp.getInstance().getProperty(PagesJsp.USERS_DATA);
        request.setAttribute("currentPage", page);
        return page;
    }
}
