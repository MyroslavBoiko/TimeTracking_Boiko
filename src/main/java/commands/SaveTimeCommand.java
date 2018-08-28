package commands;

import entities.User;
import manager.PagesJsp;
import services.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveTimeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        String time = request.getParameter("time");
        AssignmentsService.saveTime(((User)(request.getSession().getAttribute("user"))).getEmail(), description,time);
        return PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
    }
}
