package services;

import dao.DaoFactory;
import dao.interfaces.*;
import entities.*;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RequestsService {

    private static final Logger LOGGER = Logger.getLogger(RequestsService.class);

    public static boolean createRequest(String email, String activityDescription){
        UserDao userDao = DaoFactory.createUserDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        RequestToAddDao requestToAddDao = DaoFactory.createRequestToAddDao();

        try{
            User user = userDao.findWhereEmailEquals(email);
            Activity activity = activityDao.findWhereDescriptionEquals(activityDescription);
            RequestToAdd requestToAdd = new RequestToAdd();
            requestToAdd.setUserId(user.getUserId());
            requestToAdd.setActivityId(activity.getActivityId());
            requestToAddDao.insertNewRequestToAdd(requestToAdd);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in createRequest method.");
        }
        return false;
    }

    public static List<RequestToAdd> getAllActiveAddRequests(){
        RequestToAddDao requestDao = DaoFactory.createRequestToAddDao();
        List<RequestToAdd> requestToAdds;
        try{
           requestToAdds = requestDao.findWhereActiveEquals(true);
           return requestToAdds;
        }catch (Exception e){
            LOGGER.error("Exception in getAllActiveAddRequests method.");
            return null;
        }
    }

    public static List<Pair<String, String>> outputAddRequests(){
        List<Pair<String,String>> result = new ArrayList<>();
        UserDao userDao = DaoFactory.createUserDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        try{
            List<RequestToAdd> requestToAdd = getAllActiveAddRequests();
            for(RequestToAdd request : requestToAdd){
                User user = userDao.findWhereUserIdEquals(request.getUserId());
                Activity activity = activityDao.findWhereActivityIdEquals(request.getActivityId());
                result.add(new Pair<>(user.getEmail(), activity.getDescription()));
            }
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in outputAddRequests method.");
        }
        return null;
    }

    public static List<Pair<String, String>> outputDeleteRequests(){
        List<Pair<String,String>> result = new ArrayList<>();
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        try{
            List<RequestToDelete> requestToDelete = getAllActiveDeleteRequests();
            for(RequestToDelete request : requestToDelete){
                Assignment assignment = assignmentDao.findWhereAssignIdAndIsActiveEquals(request.getAssignId(), true);
                if(assignment != null){
                    result.add(new Pair<>(assignment.getUserEmail(), assignment.getActivityDescription()));
                }
            }
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in outputDeleteRequests method.");
        }
        return null;
    }

    public static List<RequestToDelete> getAllActiveDeleteRequests(){
        RequestToDeleteDao requestDao = DaoFactory.createRequestToDeleteDao();
        List<RequestToDelete> requestToDeletes;
        try{
            requestToDeletes = requestDao.findWhereActiveEquals(true);
            return requestToDeletes;
        }catch (Exception e){
            LOGGER.error("Exception in getAllActiveDeleteRequests method.");
        }
        return null;
    }
    public static List<RequestToAdd> getUserRequestsToAdd(String email){
        RequestToAddDao request = DaoFactory.createRequestToAddDao();
        UserDao userDao = DaoFactory.createUserDao();

        List<RequestToAdd> result;
        try{
            User user = userDao.findWhereEmailEquals(email);
            result = request.findWhereUserIdEquals(user.getUserId());
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in getUserRequestsToAdd method.");
        }
        return null;
    }

    public static boolean setInactiveToRequest(String userEmail, String activityDescription){
        UserDao userDao = DaoFactory.createUserDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        RequestToAddDao requestDao = DaoFactory.createRequestToAddDao();
        try{
            Long activityId = activityDao.findWhereDescriptionEquals(activityDescription).getActivityId();
            Long userId = userDao.findWhereEmailEquals(userEmail).getUserId();
            requestDao.setInactiveRequestToAdd(activityId,userId);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in setInactiveToRequest method.");
        }
        return false;
    }

    public static boolean createRequestToDelete(String email, String description){
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        RequestToDeleteDao requestToDeleteDao = DaoFactory.createRequestToDeleteDao();
        UserDao userDao = DaoFactory.createUserDao();
        User user;
        Assignment assignment;
        RequestToDelete requestToDelete = new RequestToDelete();
        try{
            user = userDao.findWhereEmailEquals(email);
            assignment = assignmentDao.findWhereEmailDescriptionActiveEquals(email,description, true);
            requestToDelete.setAssignId(assignment.getAssignId());
            requestToDelete.setUserId(user.getUserId());
            requestToDeleteDao.insertNewRequestToDelete(requestToDelete);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in createRequestToDelete method.");
        }
        return false;
    }
}
