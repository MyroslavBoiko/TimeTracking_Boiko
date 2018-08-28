package dao.interfaces;

import entities.Assignment;

import java.util.List;

public interface AssignmentDao {
    List<Assignment> findAll() throws Exception;
    Assignment findWhereAssignIdEquals(Long assignId) throws Exception;
    Assignment findWhereAssignIdAndIsActiveEquals(Long assignId, boolean isActive) throws Exception;
    Assignment findWhereEmailAndDescriptionEquals(String email, String description) throws Exception;
    Assignment findWhereEmailDescriptionActiveEquals(String email, String description, boolean isActive) throws Exception;
    List<Assignment> findWhereUserEmailEquals(String userEmail) throws Exception;
    List<Assignment> findWhereUserEmailAndActiveEquals(String userEmail, boolean isActive) throws Exception;
    List<Assignment> findWhereActiveEquals(boolean isActive) throws Exception;
    List<Assignment> findByVaryingParams(String sql, Object... params) throws Exception;
    void insertNewAssignment(Assignment assignment) throws Exception;
    void updateAssignmentTotalTime(Long assignId, Long totalTime) throws Exception;
    void setInactive(String email, String description) throws Exception;

}
