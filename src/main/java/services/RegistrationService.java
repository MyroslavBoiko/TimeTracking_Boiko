package services;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import dao.interfaces.UserTypeDao;
import entities.User;
import org.apache.log4j.Logger;

public class RegistrationService {
    private static final Logger LOGGER = Logger.getLogger(RegistrationService.class);

    public static boolean performRegistration(User user){
        UserTypeDao userTypeDao = DaoFactory.createUserTypeDao();
        try{
            user.setUserTypeId(userTypeDao.findWhereTypeNameEquals("Client").getUserTypeId());
            UserDao userDao = DaoFactory.createUserDao();
            if(userDao.findWhereEmailEquals(user.getEmail())== null){
                userDao.insertNewUser(user);
                return true;
            }
        }catch (Exception e){
            LOGGER.error("Exception in performRegistration method.");
        }
        return false;
    }
}
