package commands.sidebar.client;

import commands.Command;
import entities.User;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        RequestsService service = ServiceFactory.getRequestsService();
        User user = (User)request.getSession().getAttribute("user");
        String description = request.getParameter("description");
        if(service.checkUsedActivity(user, description)){
            service.createRequest(user,description);
            page = PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
        }else {
            request.setAttribute("errorMessage", "You already have this activity.");
            page = PagesJsp.getInstance().getProperty(PagesJsp.ACTIVITIES_DATA);
        }
        request.setAttribute("currentPage", page);
        return page;
    }
}
