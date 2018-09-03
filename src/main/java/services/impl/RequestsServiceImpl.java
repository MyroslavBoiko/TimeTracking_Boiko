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
    public boolean createRequest(User user, String description) {
//        ActivityDao activityDao = DaoFactory.createActivityDao();
        RequestToAddDao requestToAddDao = DaoFactory.createRequestToAddDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        try{
            Long activityId = activityTranslateDao.findWhereDescriptionEquals(description).getActivityId();
//            Activity activity = activityDao.findWhereActivityIdEquals(activityId);
            RequestToAdd requestToAdd = new RequestToAdd();
            requestToAdd.setUserId(user.getUserId());
            requestToAdd.setActivityId(activityId);
            requestToAddDao.insertNewRequestToAdd(requestToAdd);
            return true;
        }catch (Exception e){
            LOGGER.error("Exception in createRequest method.");
        }
        return false;
    }

    @Override
    public int getCountOfRowsRequestToAdd(boolean isActive) {
        RequestToAddDao requestToAddDao = DaoFactory.createRequestToAddDao();
        try{
            return requestToAddDao.getNumberByActive(isActive);
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return 0;
    }

    @Override
    public int getCountOfRowsRequestToDelete(boolean isActive) {
        RequestToDeleteDao requestToDelete = DaoFactory.createRequestToDeleteDao();
        try{
            return requestToDelete.getNumberOfRows();
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return 0;
    }

    @Override
    public List<Pair<String,String>> getRequestsToAddPerPage(int currentPage, int recordsPerPage, String language){
        List<Pair<String,String>> result = new ArrayList<>();
        UserDao userDao = DaoFactory.createUserDao();
        RequestToAddDao requestDao = DaoFactory.createRequestToAddDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        LanguageDao languageDao = DaoFactory.createLanguageDao();
        try{
            List<RequestToAdd> requestsToAdd = requestDao.findRequestsToAddIsActiveByLimit(true, currentPage, recordsPerPage);
            for(RequestToAdd request : requestsToAdd){
                User user = userDao.findWhereUserIdEquals(request.getUserId());
                ActivityTranslate activityTranslate = activityTranslateDao.findWhereActivityIdAndLanguageIdEquals(request.getActivityId(),
                        languageDao.findWhereLanguageCodeEquals(language).getLanguageId());
                result.add(new Pair<>(user.getEmail(), activityTranslate.getDescription()));
            }
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl in getRequestsToAddPerPage method.", e);
        }
        return null;
    }

    @Override
    public List<Pair<String, String>> getRequestsToDeletePerPage(int currentPage, int recordsPerPage, String language) {
        List<Pair<String,String>> result = new ArrayList<>();
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        RequestToDeleteDao requestDao = DaoFactory.createRequestToDeleteDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        LanguageDao languageDao = DaoFactory.createLanguageDao();
        try{
            Language lang = languageDao.findWhereLanguageCodeEquals(language);
            List<RequestToDelete> requestsToDelete = requestDao.findRequestsToDeleteIsActiveByLimit(true,
                    currentPage, recordsPerPage);
            for(RequestToDelete request : requestsToDelete){
                Assignment assignment = assignmentDao.findWhereAssignIdAndIsActiveEquals(request.getAssignId(), true);
                if(assignment != null){
                    ActivityTranslate activityTranslate =
                            activityTranslateDao.findWhereDescriptionEquals(assignment.getActivityDescription());
                    Long activityId = activityTranslate.getActivityId();
                    result.add(new Pair<>(assignment.getUserEmail(),
                            activityTranslateDao.findWhereActivityIdAndLanguageIdEquals(activityId, lang.getLanguageId()).getDescription()));
                }
            }
            return result;
        }catch (Exception e){
            LOGGER.error("Exception in UsersServiceImpl during getting results from UserDao.");
        }
        return null;
    }

    @Override
    public boolean checkUsedActivity(User user, String description) {
        AssignmentDao assignmentDao = DaoFactory.createAssignmentDao();
        RequestToAddDao requestDao = DaoFactory.createRequestToAddDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();
        try{
            Long activityId = activityTranslateDao.findWhereDescriptionEquals(description).getActivityId();
            Activity activity = activityDao.findWhereActivityIdEquals(activityId);
            RequestToAdd requestToAdd = requestDao.findWhereActivityIdAndUserIdEquals(activity.getActivityId(),
                    user.getUserId(), true);
            if(requestToAdd == null) {
                Assignment assignment = assignmentDao.findWhereEmailDescriptionActiveEquals(user.getEmail(), activity.getDescription(), true);
                if (assignment == null) {
                    return true;
                }else{
                    return false;
                }
            }
        }catch (Exception e){
            LOGGER.error("Exception in checkUsedActivity method.");
        }
        return false;
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

    @Override
    public boolean setInactiveToRequest(String userEmail, String description){
        UserDao userDao = DaoFactory.createUserDao();
        ActivityDao activityDao = DaoFactory.createActivityDao();
        RequestToAddDao requestDao = DaoFactory.createRequestToAddDao();
        try{
            Long activityId = activityDao.findWhereDescriptionEquals(description).getActivityId();
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
        ActivityDao activityDao = DaoFactory.createActivityDao();
        ActivityTranslateDao activityTranslateDao = DaoFactory.createActivityTranslateDao();

        RequestToDelete requestToDelete = new RequestToDelete();
        try{
            User user = userDao.findWhereEmailEquals(email);
            Long activityId = activityTranslateDao.findWhereDescriptionEquals(description).getActivityId();
            activityDao.findWhereActivityIdEquals(activityId);
            Assignment assignment = assignmentDao.findWhereEmailDescriptionActiveEquals(email,
                    activityDao.findWhereActivityIdEquals(activityId).getDescription(),true);
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
