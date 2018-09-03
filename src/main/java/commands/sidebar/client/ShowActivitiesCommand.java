package commands.sidebar.client;

import commands.Command;
import commands.utils.Paginator;
import entities.Activity;
import entities.ActivityTranslate;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.ActivitiesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mirosha
 */
public class ShowActivitiesCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowActivitiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of ShowActivitiesCommand");
        String page = null;
        final int recordsPerPage = 5;
        ActivitiesService service = ServiceFactory.getActivitiesService();
        Paginator paginator = new Paginator(service.getCountOfRows(), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<ActivityTranslate> activities = service.getActivitiesPerPage(paginator.getCurrentPage(),
                recordsPerPage,
                (String) request.getSession().getAttribute("language"));
        request.getSession().setAttribute("activities", activities);
        request.getSession().setAttribute("pagesCount", paginator.getPagesCount());
        String type = (String)request.getSession().getAttribute("type");
        if(type.equalsIgnoreCase("Client")){
            page = PagesJsp.getInstance().getProperty(PagesJsp.ACTIVITIES_DATA);
        }else if(type.equalsIgnoreCase("Admin")){
            page = PagesJsp.getInstance().getProperty(PagesJsp.SHOW_ACTIVITIES_ADMIN);
        }
        request.setAttribute("currentPage", page);
        return page;
    }
}
