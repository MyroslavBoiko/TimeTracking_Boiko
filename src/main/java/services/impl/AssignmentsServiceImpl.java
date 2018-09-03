package services.impl;

import dao.DaoFactory;
import dao.interfaces.ActivityDao;
import dao.interfaces.AssignmentDao;
import dao.interfaces.UserDao;
import entities.Activity;
import entities.Assignment;
import entities.User;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;

import java.util.List;

public class AssignmentsServiceImpl implements AssignmentsService {

    private static final Logger LOGGER = Logger.getLogger(AssignmentsServiceImpl.class);

    private static AssignmentsServiceImpl instance;

    public static AssignmentsServiceImpl getInstance(){
        if(instance == null){
            instance = new AssignmentsServiceImpl();
        }
        return instance;
    }

    private AssignmentsServiceImpl(){}

    @Override
    public List<Assignment> getActiveAssignments(){
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

    @Override
    public boolean createAssignment(String email, String activityDescription){
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
            ServiceFactory.getRequestsService().setInactiveToRequest(email,activityDescription);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in createAssignment method.");
        }
        return false;
    }

    @Override
    public List<Assignment> getUserAssignments(String email){
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

    @Override
    public boolean saveTime(String email, String description, String time){
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

    @Override
    public boolean setAssignInactive(String email, String description){
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            assignmentDao.setInactive(email, description);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in setAssignInactive method.");
        }
        return false;
    }
    @Override
    public List<Assignment> getAssignmentsPerPage(boolean isActive, int currentPage, int recordsPerPage){
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try{
            return assignmentDao.findAssignmentsByLimitWhereIsActive(isActive, currentPage, recordsPerPage);
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return null;
    }

    @Override
    public List<Assignment> getUserAssignmentsPerPage(String email,boolean isActive, int currentPage, int recordsPerPage ){
        List<Assignment> result;
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try {
            result = assignmentDao.findAssignmentsByLimitForUser(email, isActive, currentPage, recordsPerPage);
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in getUserAssignments method.");
        }
        return null;
    }

    @Override
    public int getCountForUser(String email, boolean isActive) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try{
            return assignmentDao.getNumberForUser(email, isActive);
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return 0;
    }

    @Override
    public int getCountOfRows(boolean isActive) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try{
            return assignmentDao.getNumberByActive(isActive);
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return 0;
    }
}
