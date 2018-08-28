package dao.impl;

import dao.interfaces.RequestToDeleteDao;
import datasource.Datasource;
import entities.RequestToDelete;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private RequestToDeleteDaoImpl() {}

    public static RequestToDeleteDaoImpl getInstance(){
        if(instance == null){
            instance = new RequestToDeleteDaoImpl();
        }
        return instance;
    }

    @Override
    public List<RequestToDelete> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public RequestToDelete findWhereDeleteIdEquals(Long deleteId) throws Exception {
        return  fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_DELETE_ID_PK + " = ?", deleteId));
    }

    @Override
    public RequestToDelete findWhereAssignIdEquals(Long assignId) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_ASSIGN_ID_FK + " = ?", assignId));
    }

    @Override
    public List<RequestToDelete> findWhereUserIdEquals(Long userId) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_USER_ID_FK + " = ?", userId);
    }

    @Override
    public List<RequestToDelete> findWhereActiveEquals(boolean isActive) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_IS_ACTIVE + " = ?", isActive);
    }

    @Override
    public List<RequestToDelete> findByVaryingParams(String sql, Object... params) throws Exception {
        List<RequestToDelete> result = new ArrayList<>();
        try(Connection connection = Datasource.getDataSource().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(sql), params);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                RequestToDelete requestToDelete = new RequestToDelete();
                requestToDelete.setDeleteId(resultSet.getLong(COLUMN_DELETE_ID_PK));
                requestToDelete.setAssignId(resultSet.getLong(COLUMN_ASSIGN_ID_FK));
                requestToDelete.setUserId(resultSet.getLong(COLUMN_USER_ID_FK));
                requestToDelete.setIsActive(resultSet.getBoolean(COLUMN_IS_ACTIVE));
                result.add(requestToDelete);
            }
        }catch (SQLException e){
            LOGGER.error("Exception in findByVaryingParams method of RequestToDeleteDaoImpl class.");
            throw new SQLException();
        }
        return result;
    }

    @Override
    public void insertNewRequestToDelete(RequestToDelete requestToDelete) throws Exception {
        try(Connection connection = Datasource.getDataSource().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_INSERT_REQUEST_TO_DELETE),
                    requestToDelete.getAssignId(), requestToDelete.getUserId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewRequestToDelete method of RequestToDeleteDaoImpl class.");
            throw new SQLException();
        }
    }

    private RequestToDelete fetchSingleResult(List<RequestToDelete> requestToDeletes) {
        if(requestToDeletes.size() > 0){
            return requestToDeletes.remove(0);
        }
        return null;
    }
}
