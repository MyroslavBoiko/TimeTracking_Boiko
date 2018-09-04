package services.impl;

import dao.DaoFactory;
import dao.interfaces.ActivityDao;
import dao.interfaces.ActivityTranslateDao;
import dao.interfaces.LanguageDao;
import entities.Activity;
import entities.ActivityTranslate;
import entities.Language;
import org.apache.log4j.Logger;
import services.interfaces.ActivitiesService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirosha
 */
public class ActivitiesServiceImpl implements ActivitiesService {

    private static final Logger LOGGER = Logger.getLogger(ActivitiesServiceImpl.class);

    private static ActivitiesServiceImpl instance;

    public static ActivitiesServiceImpl getInstance() {
        if (instance == null) {
            instance = new ActivitiesServiceImpl();
        }
        return instance;
    }

    private ActivitiesServiceImpl() {
    }

    @Override
    public List<ActivityTranslate> getActivitiesPerPage(int currentPage, int recordsPerPage, String locale) {
        ActivityDao activityDao = DaoFactory.createActivityDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        LanguageDao languageDao = DaoFactory.createLanguageDao();
        Language language;
        List<Activity> activities;
        List<ActivityTranslate> resultList = new ArrayList<>();
        try {
            language = languageDao.findWhereLanguageCodeEquals(locale);
            activities = activityDao.findActivitiesByLimit(currentPage, recordsPerPage);
            if (language == null) {
                return null;
            }
            for (Activity activity : activities) {
                resultList.add(activityTranslateDao.findWhereActivityIdAndLanguageIdEquals(activity.getActivityId(), language.getLanguageId()));
            }
            return resultList;
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return null;
    }

    @Override
    public int getCountOfRows() {
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try {
            return activityDao.getNumberOfRows();
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return 0;
    }
}
