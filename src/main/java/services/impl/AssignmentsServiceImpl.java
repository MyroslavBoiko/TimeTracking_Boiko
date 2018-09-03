package services.impl;

import annotation.Transaction;
import dao.DaoFactory;
import dao.interfaces.*;
import entities.*;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirosha
 */
public class AssignmentsServiceImpl implements AssignmentsService {

    private static final Logger LOGGER = Logger.getLogger(AssignmentsServiceImpl.class);

    private static AssignmentsServiceImpl instance;

    public static AssignmentsServiceImpl getInstance() {
        if (instance == null) {
            instance = new AssignmentsServiceImpl();
        }
        return instance;
    }

    private AssignmentsServiceImpl() {
    }

    @Override
    public List<Assignment> getActiveAssignments(String language) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        LanguageDao languageDao = DaoFactory.createLanguageDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        List<Assignment> result = new ArrayList<>();
        try {
            Long langId = languageDao.findWhereLanguageCodeEquals(language).getLanguageId();
            List<Assignment> assignments = assignmentDao.findWhereActiveEquals(true);
            for (Assignment assignment : assignments) {
                Long activityId = activityDao.findWhereDescriptionEquals(assignment.getActivityDescription()).getActivityId();
                assignment.setActivityDescription(activityTranslateDao.findWhereActivityIdAndLanguageIdEquals(activityId, langId).getDescription());
                result.add(assignment);
            }
            return assignments;
        } catch (Exception e) {
            LOGGER.error("Exception in getActiveAssignments method.", e);
        }
        return null;
    }

    @Override
    public boolean createAssignment(String email, String description) {
        UserDao userDao = DaoFactory.createUserDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        try {
            User user = userDao.findWhereEmailEquals(email);
            ActivityTranslate activityTranslate = activityTranslateDao.findWhereDescriptionEquals(description);
            Activity activity = activityDao.findWhereActivityIdEquals(activityTranslate.getActivityId());
            Assignment assignment = new Assignment();
            assignment.setUserEmail(user.getEmail());
            assignment.setActivityDescription(activity.getDescription());
            assignmentDao.insertNewAssignment(assignment);
            ServiceFactory.getRequestsService().setInactiveToRequest(email, activity.getDescription());
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception in createAssignment method.", e);
        }
        return false;
    }

    @Override
    public List<Assignment> getUserAssignments(String email) {
        List<Assignment> result;
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            result = assignmentDao.findWhereUserEmailAndActiveEquals(email, true);
            return result;
        } catch (Exception e) {
            LOGGER.error("Exception in getUserAssignments method.", e);
        }
        return null;
    }

    @Override
    public boolean saveTime(String email, String description, String time) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        try {
            Long activityId = activityTranslateDao.findWhereDescriptionEquals(description).getActivityId();
            Activity activity = activityDao.findWhereActivityIdEquals(activityId);
            Assignment assignment = assignmentDao.findWhereEmailDescriptionActiveEquals(email, activity.getDescription(), true);
            Long longTime = Long.parseLong(time);
            assignmentDao.updateAssignmentTotalTime(assignment.getAssignId(), longTime);
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception in saveTime method.", e);
        }
        return false;
    }

    @Transaction
    @Override
    public boolean setAssignInactive(String email, String description) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        RequestToDeleteDao requestToDeleteDao = DaoFactory.createRequestToDeleteDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try {
            Long activityId = activityTranslateDao.findWhereDescriptionEquals(description).getActivityId();
            Long assignId = assignmentDao.findWhereEmailDescriptionActiveEquals(email,
                    activityDao.findWhereActivityIdEquals(activityId).getDescription(), true).getAssignId();
            requestToDeleteDao.setInactive(assignId);
            assignmentDao.setInactive(email, activityDao.findWhereActivityIdEquals(activityId).getDescription());
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception in setAssignInactive method.", e);
        }
        return false;
    }

    @Override
    public List<Assignment> getAssignmentsPerPage(boolean isActive, int currentPage, int recordsPerPage) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            return assignmentDao.findAssignmentsByLimitWhereIsActive(isActive, currentPage, recordsPerPage);
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return null;
    }

    @Override
    public List<Assignment> getUserAssignmentsPerPage(String email, String language, boolean isActive, int currentPage, int recordsPerPage) {
        List<Assignment> assignments;
        List<Assignment> result = new ArrayList<>();
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        LanguageDao languageDao = DaoFactory.createLanguageDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        ActivityTranslateDao activityTranslate = DaoFactory.createActivityTranslateDao();
        try {
            Language lang = languageDao.findWhereLanguageCodeEquals(language);
            assignments = assignmentDao.findAssignmentsByLimitForUser(email, isActive, currentPage, recordsPerPage);
            for (Assignment assignment : assignments) {
                Activity activity = activityDao.findWhereDescriptionEquals(assignment.getActivityDescription());
                ActivityTranslate translate = activityTranslate.findWhereActivityIdAndLanguageIdEquals(activity.getActivityId(), lang.getLanguageId());
                assignment.setActivityDescription(translate.getDescription());
                result.add(assignment);
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("Exception in getUserAssignments method.", e);
        }
        return null;
    }

    @Override
    public int getCountForUser(String email, boolean isActive) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            return assignmentDao.getNumberForUser(email, isActive);
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return 0;
    }

    @Override
    public int getCountOfRows(boolean isActive) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            return assignmentDao.getNumberByActive(isActive);
        } catch (Exception e) {
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.", e);
        }
        return 0;
    }
}
