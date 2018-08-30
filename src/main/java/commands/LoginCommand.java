package commands;

import commands.utils.CommandUtils;
import entities.User;
import entities.UserType;
import manager.PagesJsp;
import services.LoginServiceImpl;
import services.ServiceFactory;
import services.interfaces.LoginService;
import utils.PasswordCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginService service = ServiceFactory.getLoginService();
        User user = service.getUser(email);
        UserType userType = service.getUserType(email);
        if (user == null) {
            page = PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
        }else{
            if (user.getPassword().equals(PasswordCrypt.encryptPassword(password))) {
                if ("Admin".equalsIgnoreCase(userType.getTypeName())) {
                    CommandUtils.saveUserInSession(request.getSession(),user, userType.getTypeName());
                    page = PagesJsp.getInstance().getProperty(PagesJsp.ADMIN);
                } else if ("Client".equalsIgnoreCase(userType.getTypeName())) {
                    CommandUtils.saveUserInSession(request.getSession(), user, userType.getTypeName());
                    page = PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
                } else {
                    page = PagesJsp.getInstance().getProperty(PagesJsp.ERROR);
                }
            }else {
                page = PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
            }
        }
        return page;
    }
}
