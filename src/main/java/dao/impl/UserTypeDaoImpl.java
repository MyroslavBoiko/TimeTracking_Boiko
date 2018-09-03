package dao.impl;

import dao.interfaces.UserTypeDao;
import datasource.ConnectionHolder;
import datasource.Datasource;
import datasource.TransactionManager;
import entities.UserType;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTypeDaoImpl implements UserTypeDao {

    private static final Logger LOGGER = Logger.getLogger(UserTypeDaoImpl.class);

    private static UserTypeDaoImpl instance;

    private static final String TABLE_USER_TYPE = "user_type";
    private static final String COLUMN_USER_TYPE_ID_PK = "user_type_id";
    private static final String COLUMN_TYPE_NAME = "type_name";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_USER_TYPE;

    private static final String SQL_INSERT_USERTYPE = "INSERT INTO " + TABLE_USER_TYPE
            + " (" + COLUMN_TYPE_NAME + ") "
            + "VALUES (?)";

    private static final String SQL_SELECT_LIMIT = SQL_SELECT + " LIMIT ?, ?";
    private static final String SQL_SELECT_COUNT ="SELECT COUNT(*) FROM" + TABLE_USER_TYPE;

    private final TransactionManager TRANSACTION_MANAGER = TransactionManager.getInstance();

    private UserTypeDaoImpl(){}

    public static UserTypeDaoImpl getInstance(){
        if(instance == null){
            instance = new UserTypeDaoImpl();
        }
        return instance;
    }

    @Override
    public List<UserType> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public UserType findWhereUserTypeIdEquals(Long userTypeId) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_USER_TYPE_ID_PK + " = ?", userTypeId));
    }

    @Override
    public UserType findWhereTypeNameEquals(String typeName) throws Exception {
        return  fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE "+ COLUMN_TYPE_NAME + " = ?", typeName));
    }

    @Override
    public List<UserType> findUserTypesByLimit(int currentPage, int recordsPerPage) throws Exception {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return findByVaryingParams(SQL_SELECT_LIMIT, start, recordsPerPage);
    }

    @Override
    public int getNumberOfRows() throws Exception {
       return getNumberOfRowsByParams(SQL_SELECT_COUNT);
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
            LOGGER.error("Exception in getNumberOfRows method of UserTypeDaoImpl class.");
            throw new SQLException();
        }
        return numOfRows;
    }

    @Override
    public List<UserType> findByVaryingParams(String sql, Object... params) throws Exception {
        List<UserType> result = new ArrayList<>();
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(sql), params);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserType userType = new UserType();
                userType.setUserTypeId(resultSet.getLong(COLUMN_USER_TYPE_ID_PK));
                userType.setTypeName(resultSet.getString(COLUMN_TYPE_NAME));
                result.add(userType);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of UserTypeDaoImpl class.");
            throw new SQLException();
        }
        return result;
    }

    @Override
    public void insertNewType(UserType userType) throws Exception {
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(SQL_INSERT_USERTYPE),
                     userType.getTypeName())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewType method of UserTypeDaoImpl class.");
            throw new SQLException();
        }
    }

    private UserType fetchSingleResult(List<UserType> userTypes) {
        if(userTypes.size() > 0){
            return userTypes.remove(0);
        }
        return null;
    }
}

