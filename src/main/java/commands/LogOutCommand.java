package commands;

import manager.PagesJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("type", null);
        return PagesJsp.getInstance().getProperty(PagesJsp.INDEX);
    }
}
