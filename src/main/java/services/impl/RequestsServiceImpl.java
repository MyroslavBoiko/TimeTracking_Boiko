package services.impl;

import annotation.Transaction;
import dao.DaoFactory;
import dao.interfaces.*;
import entities.*;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import services.interfaces.RequestsService;

import java.util.ArrayList;
import java.util.List;

public class RequestsServiceImpl implements RequestsService {

    private static final Logger LOGGER = Logger.getLogger(RequestsServiceImpl.class);

    private static RequestsServiceImpl instance;

    public static RequestsServiceImpl getInstance(){
        if(instance == null){
            instance = new RequestsServiceImpl();
        }
        return instance;
    }

    private RequestsServiceImpl(){}

    @Transaction
    @Override
    public boolean createRequest(String email, String activityDescription) {
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

    @Override
    public int getCountOfRowsRequestToAdd() {
        RequestToAddDao requestToAddDao = DaoFactory.createRequestToAddDao();
        try{
            return requestToAddDao.getNumberOfRows();
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return 0;
    }

    @Override
    public int getCountOfRowsRequestToDelete() {
        return 0;
    }

    @Override
    public List<RequestToAdd> getAllActiveAddRequests(){
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

    @Override
    public List<Pair<String, String>> outputAddRequests(){
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

    @Override
    public List<Pair<String, String>> outputDeleteRequests(){
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

    @Override
    public List<RequestToDelete> getAllActiveDeleteRequests(){
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

    public List<RequestToAdd> getUserRequestsToAdd(String email){
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

    @Override
    public boolean setInactiveToRequest(String userEmail, String activityDescription){
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

    @Override
    public boolean createRequestToDelete(String email, String description){
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
