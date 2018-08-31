package commands;

import entities.User;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import utils.PasswordCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        User user = new User();
        try {
            user.setEmail(request.getParameter("email"));
            user.setPassword(PasswordCrypt.encryptPassword(request.getParameter("password")));
            user.setFirstName(request.getParameter("first_name"));
            user.setLastName(request.getParameter("last_name"));
        }catch (Exception e){
            LOGGER.error("Exception in RegistrationCommand");
        }

        if(ServiceFactory.getRegistrationService().performRegistration(user)){
            page = PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
        }else {
            page = PagesJsp.getInstance().getProperty(PagesJsp.ERROR);
        }
        return page;
    }
}
