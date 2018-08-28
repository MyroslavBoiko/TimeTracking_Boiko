package services;

import dao.DaoFactory;
import dao.interfaces.ActivityDao;
import entities.Activity;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivitiesService {

    private static final Logger LOGGER = Logger.getLogger(ActivitiesService.class);

    public static List<Activity> getAllActivities(){
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try{
            return activityDao.findAll();
        }catch (Exception e){
            LOGGER.error("Exception in getAllActivities method.");
        }
        return null;
    }
}
