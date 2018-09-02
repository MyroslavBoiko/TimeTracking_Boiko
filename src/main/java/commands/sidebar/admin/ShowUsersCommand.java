package commands.sidebar.admin;

import com.sun.org.apache.regexp.internal.RE;
import commands.Command;
import commands.utils.Paginator;
import entities.User;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUsersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        final int recordsPerPage = 5;
        UsersService service = ServiceFactory.getUsersService();
        Paginator paginator = new Paginator(service.getCountOfRows(), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if(pageParameter != null){
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<User> users = service.getUsersPerPage(paginator.getCurrentPage(), recordsPerPage);
        request.setAttribute("users", users);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        page =  PagesJsp.getInstance().getProperty(PagesJsp.USERS_DATA);
        request.setAttribute("currentPage", page);
        return page;
    }
}
