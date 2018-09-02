package commands;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import commands.utils.CommandUtils;
import entities.User;
import manager.Message;
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
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = new User();
        try {
            if(CommandUtils.checkRegisterFields(request, email, password, firstName, lastName)){
                user.setEmail(email);
                user.setPassword(PasswordCrypt.encryptPassword(password));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if(ServiceFactory.getRegistrationService().performRegistration(user)){
                    page = PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
                }else {
                    page = PagesJsp.getInstance().getProperty(PagesJsp.REGISTRATION);
                }
            }else {
                page = PagesJsp.getInstance().getProperty(PagesJsp.REGISTRATION);
            }
        }catch (Exception e){
            LOGGER.error("Exception in RegistrationCommand");
        }
        request.setAttribute("currentPage", page);
        return page;
    }
}
