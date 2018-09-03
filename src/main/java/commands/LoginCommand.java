package commands;

import commands.utils.CommandUtils;
import entities.User;
import entities.UserType;
import manager.Message;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.LoginService;
import utils.PasswordCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginService service = ServiceFactory.getLoginService();
        User user = service.getUser(email);
        UserType userType = service.getUserType(email);
        if (user == null) {
            request.setAttribute("errorMessage", Message.LOGIN_OR_PASS_ERROR);
            page = PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
        } else {
            if (user.getPassword().equals(PasswordCrypt.encryptPassword(password))) {
                if ("Admin".equalsIgnoreCase(userType.getTypeName())) {
                    CommandUtils.saveUserInSession(request.getSession(), user, userType.getTypeName());
                    page = PagesJsp.getInstance().getProperty(PagesJsp.ADMIN);
                } else if ("Client".equalsIgnoreCase(userType.getTypeName())) {
                    CommandUtils.saveUserInSession(request.getSession(), user, userType.getTypeName());
                    page = PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
                } else {
                    page = PagesJsp.getInstance().getProperty(PagesJsp.ERROR);
                }
            } else {
                request.setAttribute("errorMessage", Message.LOGIN_OR_PASS_ERROR);
                page = PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
            }
        }
        request.setAttribute("currentPage", page);
        return page;
    }
}
