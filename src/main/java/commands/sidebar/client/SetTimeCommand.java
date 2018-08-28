package commands.sidebar.client;

import commands.Command;
import entities.Assignment;
import manager.PagesJsp;
import services.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTimeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] temp = request.getParameter("assignment").split(":",2);
        String description = temp[0].trim();
        request.setAttribute("description", description);
        return PagesJsp.getInstance().getProperty(PagesJsp.SET_TIME);
    }
}
