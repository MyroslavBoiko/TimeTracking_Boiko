package dao.interfaces;

import entities.RequestToAdd;

import java.util.List;

public interface RequestToAddDao {
    List<RequestToAdd> findAll() throws Exception;
    RequestToAdd findWhereAddIdEquals(Long addId) throws Exception;
    List<RequestToAdd> findWhereActivityIdEquals(Long activityId) throws Exception;
    List<RequestToAdd> findWhereUserIdEquals(Long userId) throws Exception;
    List<RequestToAdd> findWhereActiveEquals(boolean isActive) throws Exception;
    List<RequestToAdd> findByVaryingParams(String sql, Object... params) throws Exception;
    void setInactiveRequestToAdd(Long activityId, Long userId) throws Exception;
    void insertNewRequestToAdd(RequestToAdd requestToAdd) throws Exception;

}
