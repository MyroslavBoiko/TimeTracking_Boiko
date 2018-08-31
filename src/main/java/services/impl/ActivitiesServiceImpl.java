package services.impl;

import dao.DaoFactory;
import dao.interfaces.ActivityDao;
import entities.Activity;
import org.apache.log4j.Logger;
import services.interfaces.ActivitiesService;

import java.util.List;

public class ActivitiesServiceImpl implements ActivitiesService {

    private static final Logger LOGGER = Logger.getLogger(ActivitiesServiceImpl.class);

    private static ActivitiesServiceImpl instance;

    public static ActivitiesServiceImpl getInstance(){
        if(instance == null){
            instance = new ActivitiesServiceImpl();
        }
        return instance;
    }

    private ActivitiesServiceImpl(){}

    @Override
    public List<Activity> getAllActivities(){
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try{
            return activityDao.findAll();
        }catch (Exception e){
            LOGGER.error("Exception in getAllActivities method.");
        }
        return null;
    }
    @Override
    public List<Activity> getUsersPerPage(int currentPage, int recordsPerPage){
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try{
            return activityDao.findActivitiesByLimit(currentPage, recordsPerPage);
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return null;
    }
    @Override
    public int getCountOfRows() {
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try{
            return activityDao.getNumberOfRows();
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return 0;
    }
}
