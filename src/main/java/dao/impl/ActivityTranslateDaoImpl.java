package dao.impl;

import dao.interfaces.ActivityTranslateDao;
import datasource.Datasource;
import entities.ActivityTranslate;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityTranslateDaoImpl implements ActivityTranslateDao {

    private static final Logger LOGGER = Logger.getLogger(ActivityTranslateDaoImpl.class);

    private static ActivityTranslateDaoImpl instance;

    private static final String TABLE_ACTIVITY_TRANSLATE = "activity_translate";
    private static final String COLUMN_TRANSLATE_ID_PK = "translate_id";
    private static final String COLUMN_LANGUAGE_ID_FK = "language_id";
    private static final String COLUMN_ACTIVITY_ID_FK = "activity_id";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String SQL_SELECT = "SELECT * FROM " + TABLE_ACTIVITY_TRANSLATE;

    private static final String SQL_INSERT_TRANSLATE = "INSERT INTO " + TABLE_ACTIVITY_TRANSLATE
            + " (" + COLUMN_LANGUAGE_ID_FK + ","
            + COLUMN_ACTIVITY_ID_FK + ","
            + COLUMN_DESCRIPTION + ") "
            + "VALUES (?,?,?)";
    private static final String SQL_SELECT_BY_TRANSLATE_ID = SQL_SELECT
            + " WHERE " + COLUMN_TRANSLATE_ID_PK + " = ?";
    private static final String SQL_SELECT_BY_LANGUAGE_ID = SQL_SELECT
            + " WHERE " + COLUMN_LANGUAGE_ID_FK + " = ?";
    private static final String SQL_SELECT_BY_ACTIVITY_ID = SQL_SELECT
            + " WHERE " + COLUMN_ACTIVITY_ID_FK + " = ?";

    private ActivityTranslateDaoImpl() {}

    public static ActivityTranslateDaoImpl getInstance(){
        if(instance == null){
            instance = new ActivityTranslateDaoImpl();
        }
        return instance;
    }

    @Override
    public List<ActivityTranslate> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public ActivityTranslate findWhereTranslateIdEquals(Long transalteId) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT_BY_TRANSLATE_ID, transalteId));
    }

    @Override
    public List<ActivityTranslate> findWhereLanguageIdEquals(Long languageId) throws Exception {
        return findByVaryingParams(SQL_SELECT_BY_LANGUAGE_ID, languageId);
    }

    @Override
    public List<ActivityTranslate> findWhereActivityIdEquals(Long activityId) throws Exception {
        return findByVaryingParams(SQL_SELECT_BY_ACTIVITY_ID, activityId);
    }

    @Override
    public List<ActivityTranslate> findByVaryingParams(String sql, Object... params) throws Exception {
        ArrayList<ActivityTranslate> result = new ArrayList<>();
        try(Connection connection = Datasource.getDataSource().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(sql), params);
                ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                ActivityTranslate activityTranslate = new ActivityTranslate();
                activityTranslate.setTranslateId(resultSet.getLong(COLUMN_TRANSLATE_ID_PK));
                activityTranslate.setLanguageId(resultSet.getLong(COLUMN_LANGUAGE_ID_FK));
                activityTranslate.setActivityId(resultSet.getLong(COLUMN_ACTIVITY_ID_FK));
                activityTranslate.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
                result.add(activityTranslate);
            }
            return result;
        }catch (SQLException e) {
            LOGGER.error("Exception in findByVaryingParams method of ActivityTranslateDaoImpl class.");
            throw new SQLException();
        }
    }

    @Override
    public void insertNewActivityTranslate(ActivityTranslate activityTranslate) throws Exception {
        try(Connection connection = Datasource.getDataSource().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_INSERT_TRANSLATE),
                    activityTranslate.getActivityId(), activityTranslate.getActivityId(), activityTranslate.getDescription())){
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewActivityTranslate method of ActivityTranslateDaoImpl class.");
            throw new SQLException();
        }
    }

   private ActivityTranslate fetchSingleResult(List<ActivityTranslate> activityTranslates) {
        if(activityTranslates.size() > 0){
            return activityTranslates.remove(0);
        }
        return null;
    }
}