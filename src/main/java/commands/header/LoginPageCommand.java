package commands.header;

import commands.Command;
import manager.PagesJsp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class LoginPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of LoginPageCommand");
        return PagesJsp.getInstance().getProperty(PagesJsp.LOGIN);
    }
}
