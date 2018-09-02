package commands.utils;

import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

public class CommandUtils {

    private static final Logger LOGGER = Logger.getLogger(CommandUtils.class);

    public static void saveUserInSession(HttpSession session, User user, String type) {
        session.setAttribute("user", user);
        session.setAttribute("type", type);
    }
}
