package dao.impl;

import dao.interfaces.RequestToDeleteDao;
import datasource.ConnectionHolder;
import datasource.Datasource;
import datasource.TransactionManager;
import entities.RequestToAdd;
import entities.RequestToDelete;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirosha
 */
public class RequestToDeleteDaoImpl implements RequestToDeleteDao {

    private static final Logger LOGGER = Logger.getLogger(RequestToDeleteDaoImpl.class);

    private static RequestToDeleteDaoImpl instance;

    private static final String TABLE_REQUEST_TO_DELETE = "request_to_delete";
    private static final String COLUMN_ASSIGN_ID_FK = "assign_id";
    private static final String COLUMN_DELETE_ID_PK = "delete_id";
    private static final String COLUMN_USER_ID_FK = "user_id";
    private static final String COLUMN_IS_ACTIVE = "is_active";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_REQUEST_TO_DELETE;
    private static final String SQL_INSERT_REQUEST_TO_DELETE = "INSERT INTO " + TABLE_REQUEST_TO_DELETE
            + " (" + COLUMN_ASSIGN_ID_FK + ","
            + COLUMN_USER_ID_FK + ") "
            + "VALUES (?,?)";
    private static final String SQL_SELECT_LIMIT = SQL_SELECT + " LIMIT ?, ?";
    private static final String SQL_SELECT_LIMIT_ACTIVE = SQL_SELECT + " WHERE " + COLUMN_IS_ACTIVE + " = ?" + " LIMIT ?, ?";
    private static final String SQL_SELECT_COUNT = "SELECT COUNT(*) FROM " + TABLE_REQUEST_TO_DELETE;
    private static final String SQL_SELECT_COUNT_IS_ACTIVE = "SELECT COUNT(*) FROM " + TABLE_REQUEST_TO_DELETE
            + " WHERE " + COLUMN_IS_ACTIVE + " = ?";
    private static final String SQL_UPDATE_SET_INACTIVE = "UPDATE " + TABLE_REQUEST_TO_DELETE
            + " SET " + COLUMN_IS_ACTIVE + " = false"
            + " WHERE " + COLUMN_ASSIGN_ID_FK + " = ?";
    private final TransactionManager TRANSACTION_MANAGER = TransactionManager.getInstance();

    private RequestToDeleteDaoImpl() {
    }

    public static RequestToDeleteDaoImpl getInstance() {
        if (instance == null) {
            instance = new RequestToDeleteDaoImpl();
        }
        return instance;
    }

    @Override
    public List<RequestToDelete> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public List<RequestToDelete> findWhereActiveEquals(boolean isActive) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_IS_ACTIVE + " = ?", isActive);
    }

    @Override
    public List<RequestToDelete> findRequestsToDeleteIsActiveByLimit(boolean isActive, int currentPage, int recordsPerPage) throws Exception {
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
            LOGGER.error("Exception in getNumberOfRows method of RequestToDeleteDaoImpl class.", e);
            throw new SQLException();
        }
        return numOfRows;
    }

    @Override
    public List<RequestToDelete> findByVaryingParams(String sql, Object... params) throws Exception {
        List<RequestToDelete> result = new ArrayList<>();
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(sql), params);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RequestToDelete requestToDelete = new RequestToDelete();
                requestToDelete.setDeleteId(resultSet.getLong(COLUMN_DELETE_ID_PK));
                requestToDelete.setAssignId(resultSet.getLong(COLUMN_ASSIGN_ID_FK));
                requestToDelete.setUserId(resultSet.getLong(COLUMN_USER_ID_FK));
                requestToDelete.setIsActive(resultSet.getBoolean(COLUMN_IS_ACTIVE));
                result.add(requestToDelete);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of RequestToDeleteDaoImpl class.", e);
            throw new SQLException();
        }
        return result;
    }

    @Override
    public void insertNewRequestToDelete(RequestToDelete requestToDelete) throws Exception {
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(SQL_INSERT_REQUEST_TO_DELETE),
                     requestToDelete.getAssignId(), requestToDelete.getUserId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewRequestToDelete method of RequestToDeleteDaoImpl class.", e);
            throw new SQLException();
        }
    }

    @Override
    public void setInactive(Long assignId) throws Exception {
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement =
                     PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(SQL_UPDATE_SET_INACTIVE), assignId)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in setInactive method of AssignmentDaoImpl class.", e);
            throw new SQLException();
        }
    }

    private RequestToDelete fetchSingleResult(List<RequestToDelete> requestToDeletes) {
        if (requestToDeletes.size() > 0) {
            return requestToDeletes.remove(0);
        }
        return null;
    }
}
