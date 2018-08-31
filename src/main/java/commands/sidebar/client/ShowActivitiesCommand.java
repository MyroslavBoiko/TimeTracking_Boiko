package commands.sidebar.client;

import commands.Command;
import commands.utils.Paginator;
import entities.Activity;
import manager.PagesJsp;
import services.ServiceFactory;
import services.interfaces.ActivitiesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowActivitiesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int recordsPerPage = 5;
        ActivitiesService service = ServiceFactory.getActivitiesService();
        Paginator paginator = new Paginator(service.getCountOfRows(), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if(pageParameter != null){
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<Activity> activities = service.getActivitiesPerPage(paginator.getCurrentPage(), recordsPerPage);
        request.setAttribute("activities", activities);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.ACTIVITIES_DATA);
    }
}
