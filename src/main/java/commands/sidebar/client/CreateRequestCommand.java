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
        String email = ((User)request.getSession().getAttribute("user")).getEmail();
        RequestsService service = ServiceFactory.getRequestsService();
        service.createRequest(email,request.getParameter("description"));
        return PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
    }
}
