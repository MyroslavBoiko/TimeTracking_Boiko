package dao.impl;

import dao.interfaces.RequestToAddDao;
import datasource.ConnectionHolder;
import datasource.Datasource;
import datasource.TransactionManager;
import entities.RequestToAdd;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of RequestToAddDao interface.
 *
 * @author Mirosha
 */
public class RequestToAddDaoImpl implements RequestToAddDao {

    private static final Logger LOGGER = Logger.getLogger(RequestToAddDaoImpl.class);

    private static RequestToAddDaoImpl instance;

    private static final String TABLE_REQUEST_TO_ADD = "request_to_add";
    private static final String COLUMN_ADD_ID_PK = "add_id";
    private static final String COLUMN_ACTIVITY_ID_FK = "activity_id";
    private static final String COLUMN_USER_ID_FK = "user_id";
    private static final String COLUMN_IS_ACTIVE = "is_active";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_REQUEST_TO_ADD;

    private static final String SQL_SELECT_WHERE_ACTIVE = SQL_SELECT + " WHERE " + COLUMN_IS_ACTIVE + " = ?";

    private static final String SQL_SELECT_WHERE_ACTIVITY_USER_ACTIVE = SQL_SELECT
            + " WHERE " + COLUMN_ACTIVITY_ID_FK + " = ?"
            + " AND " + COLUMN_USER_ID_FK + " = ?"
            + " AND " + COLUMN_IS_ACTIVE + " = ?";

    private static final String SQL_INSERT_REQUEST_TO_ADD = "INSERT INTO " + TABLE_REQUEST_TO_ADD
            + " (" + COLUMN_ACTIVITY_ID_FK + ","
            + COLUMN_USER_ID_FK + ")"
            + "VALUES (?,?)";

    private static final String SQL_UPDATE_SET_INACTIVE = "UPDATE " + TABLE_REQUEST_TO_ADD + " SET "
            + COLUMN_IS_ACTIVE + " = " + false
            + " WHERE " + COLUMN_USER_ID_FK + " = ?" + " AND "
            + COLUMN_ACTIVITY_ID_FK + " = ?";

    private static final String SQL_SELECT_LIMIT = SQL_SELECT + " LIMIT ?, ?";

    private static final String SQL_SELECT_LIMIT_ACTIVE = SQL_SELECT + " WHERE " + COLUMN_IS_ACTIVE + " = ?" + " LIMIT ?, ?";

    private static final String SQL_SELECT_COUNT = "SELECT COUNT(*) FROM " + TABLE_REQUEST_TO_ADD;

    private static final String SQL_SELECT_COUNT_IS_ACTIVE = "SELECT COUNT(*) FROM " + TABLE_REQUEST_TO_ADD
            + " WHERE " + COLUMN_IS_ACTIVE + " = ?";

    private final TransactionManager TRANSACTION_MANAGER = TransactionManager.getInstance();

    private RequestToAddDaoImpl() {
    }

    public static RequestToAddDaoImpl getInstance() {
        if (instance == null) {
            instance = new RequestToAddDaoImpl();
        }
        return instance;
    }

    @Override
    public List<RequestToAdd> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public List<RequestToAdd> findWhereActiveEquals(boolean isActive) throws Exception {
        return findByVaryingParams(SQL_SELECT_WHERE_ACTIVE, isActive);
    }

    @Override
    public RequestToAdd findWhereActivityIdAndUserIdEquals(Long activityId, Long userId, boolean isActive) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT_WHERE_ACTIVITY_USER_ACTIVE,
                activityId, userId, isActive));
    }

    @Override
    public List<RequestToAdd> findRequestsToAddIsActiveByLimit(boolean isActive, int currentPage, int recordsPerPage) throws Exception {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return findByVaryingParams(SQL_SELECT_LIMIT_ACTIVE, isActive, start, recordsPerPage);
    }

    @Override
    public int getNumberOfRows() throws Exception {
        return getNumberOfRowsByParams(SQL_SELECT_COUNT);
    }

    @Override
    public int getNumberByActive(boolean isActive) throws Exception {
        return getNumberOfRowsByParams(SQL_SELECT_COUNT_IS_ACTIVE, isActive);
    }

    @Override
    public int getNumberOfRowsByParams(String sql, Object... params) throws Exception {
        int numOfRows = 0;
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(sql), params);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                numOfRows = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in getNumberOfRows method of RequestToAddDaoImpl class.", e);
            throw new SQLException();
        }
        return numOfRows;
    }

    @Override
    public List<RequestToAdd> findByVaryingParams(String sql, Object... params) throws Exception {
        List<RequestToAdd> result = new ArrayList<>();
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(sql), params);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                RequestToAdd requestToAdd = new RequestToAdd();
                requestToAdd.setAddId(resultSet.getLong(COLUMN_ADD_ID_PK));
                requestToAdd.setActivityId(resultSet.getLong(COLUMN_ACTIVITY_ID_FK));
                requestToAdd.setUserId(resultSet.getLong(COLUMN_USER_ID_FK));
                requestToAdd.setIsActive(resultSet.getBoolean(COLUMN_IS_ACTIVE));
                result.add(requestToAdd);
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of RequestToDeleteDaoImpl class.", e);
            throw new SQLException();
        }
    }

    @Override
    public void insertNewRequestToAdd(RequestToAdd requestToAdd) throws Exception {
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(SQL_INSERT_REQUEST_TO_ADD),
                     requestToAdd.getActivityId(), requestToAdd.getUserId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewRequestToAdd method of RequestToDeleteDaoImpl class.", e);
            throw new SQLException();
        }
    }

    @Override
    public void setInactiveRequestToAdd(Long activityId, Long userId) throws Exception {
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(SQL_UPDATE_SET_INACTIVE),
                     userId, activityId)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in setInactiveRequestToAdd method of RequestToDeleteDaoImpl class.", e);
            throw new SQLException();
        }
    }

    private RequestToAdd fetchSingleResult(List<RequestToAdd> requestToAdds) {
        if (requestToAdds.size() > 0) {
            return requestToAdds.remove(0);
        }
        return null;
    }
}
