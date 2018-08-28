package commands.utils;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandUtils {

    private static final Logger LOGGER = Logger.getLogger(CommandUtils.class);

    public static void saveUserInSession(HttpSession session, User user, String type) {
        session.setAttribute("user", user);
        session.setAttribute("type", type);
    }

    public static List<User> getUsers(){
        UserDao userDao = DaoFactory.createUserDao();
        try {
            return userDao.findAll();
        }catch (Exception e){
            LOGGER.error("Exception in getUsers method of CommandUtils class");
        }
        return null;
    }
}
