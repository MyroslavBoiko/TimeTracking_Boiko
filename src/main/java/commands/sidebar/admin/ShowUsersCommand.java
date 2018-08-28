package commands.sidebar.admin;

import commands.Command;
import commands.utils.Paginator;
import entities.User;
import manager.PagesJsp;
import services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        users = UsersService.getAllUsers();
        Paginator<User> paginator = new Paginator<>(users, 4);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("users", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.USERS_DATA);
    }
}
