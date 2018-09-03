package servlets;

import commands.*;
import commands.header.*;
import commands.sidebar.admin.*;
import commands.sidebar.client.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author Mirosha
 */
public class ControllerHelper {

    private static final Logger LOGGER = Logger.getLogger(ControllerHelper.class);

    private static ControllerHelper instance = null;
    private HashMap<String, Command> commands = new HashMap<>();

    private ControllerHelper() {
        LOGGER.info("Initializing of commands.");

        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("logout", new LogOutCommand());

        commands.put("loginPage", new LoginPageCommand());
        commands.put("registerPage", new RegisterPageCommand());
        commands.put("indexPage", new IndexPageCommand());
        commands.put("adminPage", new AdminPageCommand());
        commands.put("clientPage", new ClientPageCommand());

        commands.put("showUsers", new ShowUsersCommand());
        commands.put("showActivities", new ShowActivitiesCommand());
        commands.put("showActivitiesAdmin", new ShowActivitiesCommand());
        commands.put("createAssign", new CreateAssignCommand());
        commands.put("deleteAssign", new DeleteAssignCommand());
        commands.put("showActiveRequestsToAdd", new ShowRequestsToAddCommand());
        commands.put("showActiveRequestsToDelete", new ShowRequestsToDeleteCommand());
        commands.put("createAddRequest", new CreateRequestCommand());

        commands.put("showActiveAssignments", new ShowActiveAssignments());
        commands.put("showMyAssignments", new ShowUserAssignmentsCommand());
        commands.put("showAssignToDelete", new ShowUserAssignmentsCommand());
        commands.put("requestToDelete", new RequestToDeleteCommand());

        commands.put("setTime", new SetTimeCommand());
        commands.put("saveTime", new SaveTimeCommand());
        commands.put("locale", new LocaleCommand());

    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        Command command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }
}
