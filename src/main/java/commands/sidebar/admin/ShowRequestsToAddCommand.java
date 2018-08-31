package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import javafx.util.Pair;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRequestsToAddCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestsService service = ServiceFactory.getRequestsService();
//        List<Pair<String,String>> addRequests = service.outputAddRequests();
//        Paginator<Pair<String,String>> paginator = new Paginator<>(addRequests, 4);
//        String pageParameter = request.getParameter("page");
//        if (pageParameter != null) {
//            paginator.setCurrentPage(Integer.parseInt(pageParameter));
//        }
//        request.setAttribute("requestsToAdd", paginator.getItemsForCurrentPage());
//        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.REQUESTS_TO_ADD);
    }
}
