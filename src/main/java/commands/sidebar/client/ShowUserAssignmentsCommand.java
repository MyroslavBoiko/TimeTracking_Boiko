package commands.sidebar.client;

import commands.Command;
import commands.utils.Paginator;
import entities.Assignment;
import entities.User;
import manager.PagesJsp;
import services.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUserAssignmentsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String email = ((User)request.getSession().getAttribute("user")).getEmail();
        List<Assignment> assignments = AssignmentsService.getUserAssignments(email);
        Paginator<Assignment> paginator = new Paginator<>(assignments, 4);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("userAssignments", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());
        String choose = request.getParameter("choose");
        if(choose == null){
            page = PagesJsp.getInstance().getProperty(PagesJsp.USER_ASSIGNMENTS);
        }else{
            page = PagesJsp.getInstance().getProperty(PagesJsp.ASSIGNMENTS_TO_DELETE);
        }
        return page;

    }
}
