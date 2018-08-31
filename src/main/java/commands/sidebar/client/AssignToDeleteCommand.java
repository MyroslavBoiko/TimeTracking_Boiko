package commands.sidebar.client;

import commands.Command;
import commands.utils.Paginator;
import entities.Assignment;
import entities.User;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssignToDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int recordsPerPage = 5;
        String email = ((User)request.getSession().getAttribute("user")).getEmail();
        AssignmentsService service = ServiceFactory.getAssignmentsService();
        Paginator paginator = new Paginator(service.getCountForUser(email, true), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if(pageParameter != null){
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<Assignment> assignments = service.getUserAssignmentsPerPage(email, true, paginator.getCurrentPage(), recordsPerPage);
        request.setAttribute("userAssignments", assignments);
        request.setAttribute("pagesCount", paginator.getPagesCount());




//        AssignmentsService service = ServiceFactory.getAssignmentsService();
//        String email = ((User)request.getSession().getAttribute("user")).getEmail();
//        List<Assignment> assignments = service.getUserAssignments(email);
////        Paginator<Assignment> paginator = new Paginator<>(assignments, 4);
////        String pageParameter = request.getParameter("page");
////        if (pageParameter != null) {
////            paginator.setCurrentPage(Integer.parseInt(pageParameter));
////        }
////        request.setAttribute("userAssignments", paginator.getItemsForCurrentPage());
////        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.USER_ASSIGNMENTS);
    }
}
