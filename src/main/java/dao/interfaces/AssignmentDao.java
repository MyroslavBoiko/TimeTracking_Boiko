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

    List<Assignment> findAssignmentsByLimit(int currentPage, int recordsPerPage) throws Exception;

    List<Assignment> findAssignmentsByLimitWhereIsActive(boolean isActive, int currentPage, int recordsPerPage) throws Exception;

    List<Assignment> findAssignmentsByLimitForUser(String email, boolean isActive, int currentPage, int recordsPerPage) throws Exception;

    int getNumberForUser(String email, boolean isActive) throws Exception;

    int getNumberByActive(boolean isActive) throws Exception;

    int getNumberOfRowsByParams(String sql, Object... params) throws Exception;

    void insertNewAssignment(Assignment assignment) throws Exception;

    void updateAssignmentTotalTime(Long assignId, Long totalTime) throws Exception;

    void setInactive(String email, String description) throws Exception;

}
