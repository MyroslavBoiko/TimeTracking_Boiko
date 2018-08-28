package dao.interfaces;

import entities.RequestToDelete;

import java.util.List;

public interface RequestToDeleteDao {
    List<RequestToDelete> findAll() throws Exception;
    RequestToDelete findWhereDeleteIdEquals(Long deleteId) throws Exception;
    RequestToDelete findWhereAssignIdEquals(Long assignId) throws Exception;
    List<RequestToDelete> findWhereUserIdEquals(Long userId) throws Exception;
    List<RequestToDelete> findWhereActiveEquals(boolean isActive) throws Exception;
    List<RequestToDelete> findByVaryingParams(String sql, Object... params) throws Exception;
    void insertNewRequestToDelete(RequestToDelete requestToDelete) throws Exception;

}
