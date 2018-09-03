package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import entities.User;
import javafx.util.Pair;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.RequestsService;
import services.interfaces.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mirosha
 */
public class ShowRequestsToDeleteCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowRequestsToDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of ShowRequestsToDeleteCommand");
        String page;
        final int recordsPerPage = 5;
        RequestsService service = ServiceFactory.getRequestsService();
        Paginator paginator = new Paginator(service.getCountOfRowsRequestToDelete(true), recordsPerPage);
        String pageParameter = request.getParameter("page");
        String language = (String) request.getSession().getAttribute("language");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<Pair<String, String>> requests = service.getRequestsToDeletePerPage(paginator.getCurrentPage(), recordsPerPage, language);
        request.setAttribute("requestsToDelete", requests);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        page = PagesJsp.getInstance().getProperty(PagesJsp.REQUESTS_TO_DELETE);
        request.setAttribute("currentPage", page);
        return page;
    }
}
