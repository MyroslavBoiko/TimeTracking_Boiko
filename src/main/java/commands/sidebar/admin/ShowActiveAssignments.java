package commands.sidebar.admin;

import commands.Command;
import entities.Assignment;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowActiveAssignments implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AssignmentsService service = ServiceFactory.getAssignmentsService();
        List<Assignment> assignments = service.getActiveAssignments();
        request.setAttribute("assignments", assignments);
        return PagesJsp.getInstance().getProperty(PagesJsp.ACTIVE_ASSIGNMENTS);
    }
}
