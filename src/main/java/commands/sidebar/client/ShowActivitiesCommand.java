package commands.sidebar.client;

import commands.Command;
import commands.utils.Paginator;
import entities.Activity;
import manager.PagesJsp;
import services.ActivitiesServiceImpl;
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
        List<Activity> activities;
        ActivitiesService service = ServiceFactory.getActivitiesService();
        activities = service.getAllActivities();
        Paginator<Activity> paginator = new Paginator<>(activities, 4);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("activities", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return PagesJsp.getInstance().getProperty(PagesJsp.ACTIVITIES_DATA);
    }
}
