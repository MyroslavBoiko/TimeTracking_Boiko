package commands.utils;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import entities.User;
import manager.Message;
import org.apache.log4j.Logger;
import utils.InputChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Util class for commands.
 *
 * @author Mirosha
 */
public class CommandUtils {

    private static final Logger LOGGER = Logger.getLogger(CommandUtils.class);

    public static void saveUserInSession(HttpSession session, User user, String type) {
        session.setAttribute("user", user);
        session.setAttribute("type", type);
    }

    public static boolean checkRegisterFields(HttpServletRequest request, String email, String password, String firstName, String lastName) {
        boolean result = true;
        if (!InputChecker.checkEmail(email)) {
            request.setAttribute("emailError", Message.EMAIL_ERROR);
            result = false;
        } else {
            UserDao userDao = DaoFactory.createUserDao();
            try {
                if (userDao.findWhereEmailEquals(email) == null) {
                    request.setAttribute("emailHold", email);
                } else {
                    request.setAttribute("emailError", Message.USED_EMAIL_ERROR);
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }


        }
        if (!InputChecker.checkPassword(password)) {
            request.setAttribute("passwordError", Message.PASSWORD_ERROR);
            result = false;
        }
        if (!InputChecker.checkName(firstName)) {
            request.setAttribute("firstNameError", Message.FIRST_NAME_ERROR);
            result = false;
        } else {
            request.setAttribute("firstNameHold", firstName);
        }
        if (!InputChecker.checkName(lastName)) {
            request.setAttribute("lastNameError", Message.LAST_NAME_ERROR);
            result = false;
        } else {
            request.setAttribute("lastNameHold", lastName);
        }
        return result;
    }
}
