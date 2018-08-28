package services;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import dao.interfaces.UserTypeDao;
import entities.User;
import entities.UserType;
import org.apache.log4j.Logger;

public class LoginService{

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public static boolean isRegistered(String email, String password){
        UserDao userDao = DaoFactory.createUserDao();

        try{
            User user = userDao.findWhereEmailEquals(email);
            if(user != null){
                if(user.getPassword().equals(password)){
                    return true;
                }
            }
        }catch (Exception e){
            LOGGER.error("Exception in isRegistered method.");
        }
        return false;
    }

    public static UserType getUserType(String email){
        UserTypeDao userTypeDao = DaoFactory.createUserTypeDao();
        UserDao userDao = DaoFactory.createUserDao();
        UserType result = null;
        try{
            User user = userDao.findWhereEmailEquals(email);
            result = userTypeDao.findWhereUserTypeIdEquals(user.getUserTypeId());
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in getUserType method.");
        }
        return null;
    }

    public static User getUser(String email){
        UserDao userDao = DaoFactory.createUserDao();
        User result;
        try{
            result = userDao.findWhereEmailEquals(email);
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in getUser method.");
        }
        return null;
    }
}
