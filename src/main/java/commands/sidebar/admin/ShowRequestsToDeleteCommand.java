package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import javafx.util.Pair;
import manager.PagesJsp;
import services.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRequestsToDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pair<String,String>> deleteRequests = RequestsService.outputDeleteRequests();
        Paginator<Pair<String,String>> paginator = new Paginator<>(deleteRequests, 4);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("requestsToDelete", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.REQUESTS_TO_DELETE);
    }
}
