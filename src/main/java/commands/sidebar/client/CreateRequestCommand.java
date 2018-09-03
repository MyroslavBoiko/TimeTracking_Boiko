package commands.sidebar.client;

import commands.Command;
import entities.User;
import manager.Message;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.RequestsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class CreateRequestCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of CreateRequestCommand");
        String page;
        RequestsService service = ServiceFactory.getRequestsService();
        User user = (User) request.getSession().getAttribute("user");
        String description = request.getParameter("description");
        if (service.checkUsedActivity(user, description)) {
            service.createRequest(user, description);
            page = PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
        } else {
            request.setAttribute("errorMessage", Message.USED_ACTIVITY_ERROR);
            page = PagesJsp.getInstance().getProperty(PagesJsp.ACTIVITIES_DATA);
        }
        request.setAttribute("currentPage", page);
        return page;
    }
}
