package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import entities.RequestToAdd;
import javafx.util.Pair;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mirosha
 */
public class ShowRequestsToAddCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        final int recordsPerPage = 5;
        RequestsService service = ServiceFactory.getRequestsService();
        Paginator paginator = new Paginator(service.getCountOfRowsRequestToAdd(true), recordsPerPage);
        String pageParameter = request.getParameter("page");
        String language = (String) request.getSession().getAttribute("language");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<Pair<String, String>> requests = service.getRequestsToAddPerPage(paginator.getCurrentPage(), recordsPerPage, language);
        request.setAttribute("requestsToAdd", requests);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        page = PagesJsp.getInstance().getProperty(PagesJsp.REQUESTS_TO_ADD);
        request.setAttribute("currentPage", page);
        return page;
    }
}
