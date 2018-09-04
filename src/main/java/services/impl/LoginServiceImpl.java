package services.impl;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import dao.interfaces.UserTypeDao;
import entities.User;
import entities.UserType;
import org.apache.log4j.Logger;
import services.interfaces.LoginService;

/**
 * @author Mirosha
 */
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);


    private static LoginServiceImpl instance;

    public static LoginServiceImpl getInstance() {
        if (instance == null) {
            instance = new LoginServiceImpl();
        }
        return instance;
    }

    private LoginServiceImpl() {
    }

    @Override
    public UserType getUserType(String email) {
        UserTypeDao userTypeDao = DaoFactory.createUserTypeDao();
        UserDao userDao = DaoFactory.createUserDao();
        UserType result;
        try {
            User user = userDao.findWhereEmailEquals(email);
            result = userTypeDao.findWhereUserTypeIdEquals(user.getUserTypeId());
            return result;
        } catch (Exception e) {
            LOGGER.error("Exception in getUserType method.", e);
        }
        return null;
    }

    @Override
    public User getUser(String email) {
        UserDao userDao = DaoFactory.createUserDao();
        User result;
        try {
            result = userDao.findWhereEmailEquals(email);
            return result;
        } catch (Exception e) {
            LOGGER.error("Exception in getUser method.", e);
        }
        return null;
    }
}
