package commands.sidebar.admin;

import commands.Command;
import manager.PagesJsp;
import services.AssignmentsServiceImpl;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;
import services.interfaces.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAssignCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String element = request.getParameter("request");
        String[] info = element.split(" ", 2);
        AssignmentsService service = ServiceFactory.getAssignmentsService();
        service.setAssignInactive(info[0], info[1]);
        return PagesJsp.getInstance().getProperty(PagesJsp.ADMIN);
    }
}
