package dao.impl;

import dao.interfaces.UserDao;
import datasource.ConnectionHolder;
import datasource.Datasource;
import datasource.TransactionManager;
import entities.User;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static UserDaoImpl instance;

    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID_PK = "user_id";
    private static final String COLUMN_USER_TYPE_ID_FK = "user_type_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_USER;
    private static final String SQL_INSERT_USER = "INSERT INTO " + TABLE_USER
            + " (" + COLUMN_USER_TYPE_ID_FK + ","
            + COLUMN_EMAIL + ","
            + COLUMN_PASSWORD + ","
            + COLUMN_FIRST_NAME + ","
            + COLUMN_LAST_NAME + ") "
            + "VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_LIMIT = SQL_SELECT + " LIMIT ?, ?";
    private static final String SQL_SELECT_COUNT ="SELECT COUNT(*) FROM " + TABLE_USER;

    private final TransactionManager TRANSACTION_MANAGER = TransactionManager.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public User findWhereUserIdEquals(Long userId) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_USER_ID_PK + " = ?", userId));
    }

    @Override
    public User findWhereEmailEquals(String email) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_EMAIL + " = ?", email));
    }

    @Override
    public List<User> findWhereUserTypeIdEquals(Long userTypeId) throws Exception {
        return findByVaryingParams(SQL_SELECT + " WHERE " + COLUMN_USER_TYPE_ID_FK + " = ?", userTypeId);
    }

    @Override
    public List<User> findUsersByLimit(int currentPage, int recordsPerPage) throws Exception {
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
            LOGGER.error("Exception in getNumberOfRows method of UserDaoImpl class.");
            throw new SQLException();
        }
        return numOfRows;
    }

    @Override
    public List<User> findByVaryingParams(String sql, Object... params) throws Exception {
        List<User> result = new ArrayList<>();
        try(ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(sql), params);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong(1));
                user.setUserTypeId(resultSet.getLong(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));
                user.setLastName(resultSet.getString(6));
                result.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of UserDaoImpl class.");
            throw new SQLException();
        }
        return result;
    }


    @Override
    public void insertNewUser(User user) throws Exception {
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(SQL_INSERT_USER),
                     user.getUserTypeId(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewUser method of UserDaoImpl class.");
            throw new SQLException();
        }
    }

    private User fetchSingleResult(List<User> users) {
        if (users.size() > 0) {
            return users.remove(0);
        }
        return null;
    }
}
