package services;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import entities.User;
import org.apache.log4j.Logger;
import services.interfaces.Service;
import services.interfaces.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = Logger.getLogger(UsersServiceImpl.class);

    private static UsersServiceImpl instance;

    public static UsersServiceImpl getInstance(){
        if(instance == null){
            instance = new UsersServiceImpl();
        }
        return instance;
    }

    private UsersServiceImpl(){}

    @Override
    public List<User> getAllUsers(){
        UserDao userDao = DaoFactory.createUserDao();
        try{
            return userDao.findAll();
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return null;
    }
}
