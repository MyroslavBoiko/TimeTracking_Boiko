package dao.impl;

import dao.interfaces.ActivityDao;
import datasource.ConnectionHolder;
import datasource.TransactionManager;
import entities.Activity;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirosha
 */

public class ActivityDaoImpl implements ActivityDao {

    private static final Logger LOGGER = Logger.getLogger(ActivityDaoImpl.class);

    private static ActivityDaoImpl instance;

    private static final String TABLE_ACTIVITY = "activity";
    private static final String COLUMN_ACTIVITY_ID_PK = "activity_id";
    private static final String COLUMN_ACTIVITY_DESCRIPTION = "description";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_ACTIVITY;
    private static final String SQL_INSERT_ACTIVITY = "INSERT INTO " + TABLE_ACTIVITY
            + " (" + COLUMN_ACTIVITY_DESCRIPTION + ") "
            + "VALUES (?)";
    private static final String SQL_SELECT_BY_ACTIVITY_ID = SQL_SELECT
            + " WHERE " + COLUMN_ACTIVITY_ID_PK + " = ?";
    private static final String SQL_SELECT_BY_DESCRIPTION = SQL_SELECT
            + " WHERE " + COLUMN_ACTIVITY_DESCRIPTION + " = ?";
    private static final String SQL_SELECT_LIMIT = SQL_SELECT + " LIMIT ?, ?";
    private static final String SQL_SELECT_COUNT = "SELECT COUNT(*) FROM " + TABLE_ACTIVITY;

    private final TransactionManager TRANSACTION_MANAGER = TransactionManager.getInstance();

    private ActivityDaoImpl() {
    }

    public static ActivityDaoImpl getInstance() {
        if (instance == null) {
            instance = new ActivityDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Activity> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public Activity findWhereActivityIdEquals(Long activityId) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT_BY_ACTIVITY_ID, activityId));

    }

    @Override
    public Activity findWhereDescriptionEquals(String description) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT_BY_DESCRIPTION, description));
    }

    @Override
    public List<Activity> findActivitiesByLimit(int currentPage, int recordsPerPage) throws Exception {
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
            LOGGER.error("Exception in getNumberOfRows method of ActivityDaoImpl class.");
            throw new SQLException();
        }
        return numOfRows;
    }

    @Override
    public List<Activity> findByVaryingParams(String sql, Object... params) throws Exception {
        ArrayList<Activity> result = new ArrayList<>();
        try (ConnectionHolder connectionHolder = TRANSACTION_MANAGER.getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connectionHolder.prepareStatement(sql), params);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Activity activity = new Activity();

                activity.setActivityId(resultSet.getLong(COLUMN_ACTIVITY_ID_PK));
                activity.setDescription(resultSet.getString(COLUMN_ACTIVITY_DESCRIPTION));
                result.add(activity);
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of ActivityDaoImpl class.");
            throw new SQLException();
        }
    }

    private Activity fetchSingleResult(List<Activity> activities) {
        if (activities.size() > 0) {
            return activities.remove(0);
        }
        return null;
    }
}
