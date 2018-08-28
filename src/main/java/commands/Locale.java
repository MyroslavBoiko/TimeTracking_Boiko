package commands;

import manager.PagesJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Locale implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("lang",request.getParameter("lang"));
        return PagesJsp.getInstance().getProperty(PagesJsp.ADMIN);
    }
}
