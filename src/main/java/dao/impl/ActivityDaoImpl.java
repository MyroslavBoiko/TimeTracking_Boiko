package dao.impl;

import dao.interfaces.ActivityDao;
import datasource.Datasource;
import entities.Activity;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private ActivityDaoImpl() {}

    public static ActivityDaoImpl getInstance(){
        if(instance == null){
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
        return  fetchSingleResult(findByVaryingParams(SQL_SELECT_BY_ACTIVITY_ID, activityId));

    }

    @Override
    public Activity findWhereDescriptionEquals(String description) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT_BY_DESCRIPTION, description));
    }

    @Override
    public List<Activity> findByVaryingParams(String sql, Object... params) throws Exception {
        ArrayList<Activity> result = new ArrayList<>();
        try(Connection connection = Datasource.getDataSource().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(sql),params);
                ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                Activity activity = new Activity();
                activity.setActivityId(resultSet.getLong(COLUMN_ACTIVITY_ID_PK));
                activity.setDescription(resultSet.getString(COLUMN_ACTIVITY_DESCRIPTION));
                result.add(activity);
            }
            return result;
        }catch (SQLException e){
            LOGGER.error("Exception in findByVaryingParams method of ActivityDaoImpl class.");
            throw new SQLException();
        }
    }

    @Override
    public void insertNewActivity(Activity activity) throws Exception {
        try(Connection connection = Datasource.getDataSource().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_INSERT_ACTIVITY),
                    activity.getDescription())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewActivity method of ActivityDaoImpl class.");
            throw new SQLException();
        }
    }

    private Activity fetchSingleResult(List<Activity> activities) {
        if(activities.size() > 0){
            return activities.remove(0);
        }
        return null;
    }
}
