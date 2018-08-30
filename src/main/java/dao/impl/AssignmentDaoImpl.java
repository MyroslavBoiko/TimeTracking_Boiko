package dao.impl;

import dao.interfaces.AssignmentDao;
import datasource.Datasource;
import entities.Assignment;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

    private static final Logger LOGGER = Logger.getLogger(AssignmentDaoImpl.class);

    private static AssignmentDaoImpl instance;

    private static final String TABLE_ASSIGNMENT = "assignment";
    private static final String COLUMN_ASSIGN_ID_PK = "assign_id";
    private static final String COLUMN_ACTIVITY_DESCRIPTION_FK = "description";
    private static final String COLUMN_USER_EMAIL_FK = "user_email";
    private static final String COLUMN_IS_ACTIVE = "is_active";
    private static final String COLUMN_TOTAL_TIME = "total_time";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_ASSIGNMENT;

    private static final String SQL_INSERT_ASSIGNMENT = "INSERT INTO " + TABLE_ASSIGNMENT
            + " (" + COLUMN_ACTIVITY_DESCRIPTION_FK + ","
            + COLUMN_USER_EMAIL_FK + ") "
            + "VALUES (?,?)";

    private static final String SQL_UPDATE_ASSIGNMENT_SET_TIME = "UPDATE " + TABLE_ASSIGNMENT
            + " SET " + COLUMN_TOTAL_TIME + " = ? "
            + "WHERE " + COLUMN_ASSIGN_ID_PK + " = ?";

    private static final String SQL_UPDATE_SET_INACTIVE = "UPDATE " + TABLE_ASSIGNMENT
            + " SET " + COLUMN_IS_ACTIVE + " = false"
            + " WHERE " + COLUMN_USER_EMAIL_FK + " = ?"
            + " AND " + COLUMN_ACTIVITY_DESCRIPTION_FK + " = ?";

    private AssignmentDaoImpl() {}

    public static AssignmentDaoImpl getInstance(){
        if(instance == null){
            instance = new AssignmentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Assignment> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public Assignment findWhereAssignIdEquals(Long assignId) throws Exception {
        return  fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_ASSIGN_ID_PK + " = ?", assignId));
    }

    @Override
    public Assignment findWhereAssignIdAndIsActiveEquals(Long assignId, boolean isActive) throws Exception {
        return  fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_ASSIGN_ID_PK + " = ?" + " AND " + COLUMN_IS_ACTIVE + " = ?", assignId, isActive));
    }

    @Override
    public Assignment findWhereEmailAndDescriptionEquals(String email, String description) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT + " WHERE "
                + COLUMN_USER_EMAIL_FK + " = ?"
                + " AND " +  COLUMN_ACTIVITY_DESCRIPTION_FK + " = ?", email, description));
    }

    @Override
    public Assignment findWhereEmailDescriptionActiveEquals(String email, String description, boolean isActive) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT + " WHERE "
                + COLUMN_USER_EMAIL_FK + " = ?"
                + " AND " +  COLUMN_ACTIVITY_DESCRIPTION_FK + " = ?" + " AND " + COLUMN_IS_ACTIVE + " = ?", email, description, isActive));
    }

    @Override
    public List<Assignment> findWhereUserEmailEquals(String userEmail) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_USER_EMAIL_FK + " = ?", userEmail);
    }

    @Override
    public List<Assignment> findWhereUserEmailAndActiveEquals(String userEmail, boolean isActive) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_USER_EMAIL_FK + " = ?"
                + " AND " + COLUMN_IS_ACTIVE + " = ?", userEmail, isActive);
    }

    @Override
    public List<Assignment> findWhereActiveEquals(boolean isActive) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_IS_ACTIVE + " = ?", isActive);
    }

    @Override
    public List<Assignment> findByVaryingParams(String sql, Object... params) throws Exception {
        ArrayList<Assignment> result = new ArrayList<>();
        try (Connection connection = Datasource.getInstance().getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(sql),params);
             ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignId(resultSet.getLong(COLUMN_ASSIGN_ID_PK));
                assignment.setActivityDescription(resultSet.getString(COLUMN_ACTIVITY_DESCRIPTION_FK));
                assignment.setUserEmail(resultSet.getString(COLUMN_USER_EMAIL_FK));
                assignment.setIsActive(resultSet.getBoolean(COLUMN_IS_ACTIVE));
                assignment.setTotalTime(resultSet.getLong(COLUMN_TOTAL_TIME));
                result.add(assignment);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of AssignmentDaoImpl class.");
            throw new SQLException();
        }
        return result;
    }

    @Override
    public void insertNewAssignment(Assignment assignment) throws Exception {

        try(Connection  connection = Datasource.getInstance().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_INSERT_ASSIGNMENT),
                    assignment.getActivityDescription(), assignment.getUserEmail())){
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewAssignment method of AssignmentDaoImpl class.");
            throw new SQLException();
        }
    }

    @Override
    public void updateAssignmentTotalTime(Long assignId, Long totalTime) throws Exception {
        try(Connection connection = Datasource.getInstance().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_UPDATE_ASSIGNMENT_SET_TIME),
                totalTime, assignId)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in updateAssignmentTotalTime method of AssignmentDaoImpl class.");
            throw new SQLException();
        }
    }

    @Override
    public void setInactive(String email, String description) throws Exception {
        try(Connection connection = Datasource.getInstance().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_UPDATE_SET_INACTIVE),
                    email, description)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in setInactive method of AssignmentDaoImpl class.");
            throw new SQLException();
        }
    }

    private Assignment fetchSingleResult(List<Assignment> assignments) {
        if(assignments.size() > 0){
            return assignments.remove(0);
        }
        return null;
    }
}
