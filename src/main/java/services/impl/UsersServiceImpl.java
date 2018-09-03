package services.impl;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import entities.User;
import org.apache.log4j.Logger;
import services.interfaces.UsersService;

import java.util.List;

/**
 * @author Mirosha
 */
public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = Logger.getLogger(UsersServiceImpl.class);

    private static UsersServiceImpl instance;

    public static UsersServiceImpl getInstance() {
        if (instance == null) {
            instance = new UsersServiceImpl();
        }
        return instance;
    }

    private UsersServiceImpl() {
    }

    @Override
    public List<User> getUsersPerPage(int currentPage, int recordsPerPage) {
        UserDao userDao = DaoFactory.createUserDao();
        try {
            return userDao.findUsersByLimit(currentPage, recordsPerPage);
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return null;
    }

    @Override
    public int getCountOfRows() {
        UserDao userDao = DaoFactory.createUserDao();
        try {
            return userDao.getNumberOfRows();
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return 0;
    }
}
