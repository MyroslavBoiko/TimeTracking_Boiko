package commands;

import manager.PagesJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("lang",request.getParameter("localeLang"));
        String page = request.getParameter("pageParam");
        if( page == null || page.equals("")){
            return PagesJsp.getInstance().getProperty(PagesJsp.INDEX);
        }else {
            return page;
        }

    }
}
