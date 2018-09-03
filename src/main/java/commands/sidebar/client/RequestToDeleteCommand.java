package commands.sidebar.client;

import commands.Command;
import entities.User;
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
public class RequestToDeleteCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RequestToDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of RequestToDeleteCommand");
        String page;
        String[] temp = request.getParameter("request").split(":", 2);
        String description = temp[0].trim();
        String email = ((User) request.getSession().getAttribute("user")).getEmail();
        RequestsService service = ServiceFactory.getRequestsService();
        service.createRequestToDelete(email, description);
        page = PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
        request.setAttribute("currentPage", page);
        return page;
    }
}
