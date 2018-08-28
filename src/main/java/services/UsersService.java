package services;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import entities.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UsersService {

    private static final Logger LOGGER = Logger.getLogger(UsersService.class);

    public static List<User> getAllUsers(){
        UserDao userDao = DaoFactory.createUserDao();
        try{
            return userDao.findAll();
        }catch (Exception e){
            LOGGER.error("Exception in UsersService during getting results from UserDao.");
        }
        return null;
    }
}
