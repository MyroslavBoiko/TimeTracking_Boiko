package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import entities.User;
import javafx.util.Pair;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.RequestsService;
import services.interfaces.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRequestsToDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int recordsPerPage = 5;
        RequestsService service = ServiceFactory.getRequestsService();
        Paginator paginator = new Paginator(service.getCountOfRowsRequestToDelete(), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if(pageParameter != null){
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<Pair<String, String>> requests = service.getRequestsToDeletePerPage(paginator.getCurrentPage(), recordsPerPage);
        request.setAttribute("requestsToDelete", requests);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.REQUESTS_TO_DELETE);
    }
}
