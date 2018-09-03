package commands.header;

import commands.Command;
import manager.PagesJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class IndexPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PagesJsp.getInstance().getProperty(PagesJsp.INDEX);
        request.setAttribute("currentPage", page);
        return page;
    }
}
