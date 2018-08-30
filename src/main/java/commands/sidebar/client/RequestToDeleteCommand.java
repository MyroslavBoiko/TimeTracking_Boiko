package commands.sidebar.client;

import commands.Command;
import entities.User;
import manager.PagesJsp;
import services.RequestsServiceImpl;
import services.ServiceFactory;
import services.interfaces.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestToDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] temp = request.getParameter("request").split(":",2);
        String description = temp[0].trim();
        String email = ((User)request.getSession().getAttribute("user")).getEmail();
        RequestsService service = ServiceFactory.getRequestsService();
        service.createRequestToDelete(email, description);
        return PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
    }
}
