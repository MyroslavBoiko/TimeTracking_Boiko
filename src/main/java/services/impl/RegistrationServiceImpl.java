package services.impl;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import dao.interfaces.UserTypeDao;
import entities.User;
import org.apache.log4j.Logger;
import services.interfaces.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

    private static RegistrationServiceImpl instance;

    public static RegistrationServiceImpl getInstance(){
        if(instance == null){
            instance = new RegistrationServiceImpl();
        }
        return instance;
    }

    private RegistrationServiceImpl(){}

    @Override
    public boolean performRegistration(User user) {
        UserTypeDao userTypeDao = DaoFactory.createUserTypeDao();
        try {
            user.setUserTypeId(userTypeDao.findWhereTypeNameEquals("Client").getUserTypeId());
            UserDao userDao = DaoFactory.createUserDao();
            userDao.insertNewUser(user);
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception in performRegistration method.");
        }
        return false;
    }
}
