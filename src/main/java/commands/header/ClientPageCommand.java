package commands.header;

import commands.Command;
import manager.PagesJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
    }
}
