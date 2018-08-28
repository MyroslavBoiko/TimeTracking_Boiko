package services;

import dao.DaoFactory;
import dao.interfaces.ActivityDao;
import dao.interfaces.AssignmentDao;
import dao.interfaces.UserDao;
import entities.Activity;
import entities.Assignment;
import entities.User;
import org.apache.log4j.Logger;

import java.util.List;

public class AssignmentsService {

    private static final Logger LOGGER = Logger.getLogger(AssignmentsService.class);

    public static List<Assignment> getActiveAssignments(){
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        List<Assignment> assignments;
        try{
           assignments = assignmentDao.findWhereActiveEquals(true);
           return assignments;
        }catch (Exception e){
            LOGGER.error("Exception in getActiveAssignments method.");
        }
        return null;
    }

    public static boolean createAssignment(String email, String activityDescription){
        UserDao userDao = DaoFactory.createUserDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try{
            User user = userDao.findWhereEmailEquals(email);
            Activity activity = activityDao.findWhereDescriptionEquals(activityDescription);
            Assignment assignment = new Assignment();
            assignment.setUserEmail(user.getEmail());
            assignment.setActivityDescription(activity.getDescription());
            assignmentDao.insertNewAssignment(assignment);
            RequestsService.setInactiveToRequest(email,activityDescription);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in createAssignment method.");
        }
        return false;
    }

    public static List<Assignment> getUserAssignments(String email){
        List<Assignment> result;
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            result = assignmentDao.findWhereUserEmailAndActiveEquals(email, true);
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in getUserAssignments method.");
        }
        return null;
    }

    public static boolean saveTime(String email, String description, String time){
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try{
            Assignment assignment = assignmentDao.findWhereEmailDescriptionActiveEquals(email, description, true);
            Long longTime = Long.parseLong(time);
            System.out.println(longTime);
            System.out.println(assignment.getAssignId());
            assignmentDao.updateAssignmentTotalTime(assignment.getAssignId(), longTime);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in saveTime method.");
        }
        return false;
    }

    public static boolean setAssignInactive(String email, String descrription){
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            assignmentDao.setInactive(email, descrription);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in setAssignInactive method.");
        }
        return false;
    }
}
