package commands.sidebar.client;

import commands.Command;
import manager.PagesJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class SetTimeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String[] temp = request.getParameter("assignment").split(":", 2);
        String description = temp[0].trim();
        request.setAttribute("description", description);
        page = PagesJsp.getInstance().getProperty(PagesJsp.SET_TIME);
        request.setAttribute("currentPage", page);
        return page;
    }
}
