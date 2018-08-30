package dao.impl;

import dao.interfaces.LanguageDao;
import datasource.Datasource;
import entities.Language;
import org.apache.log4j.Logger;
import utils.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDaoImpl implements LanguageDao {

    private static final Logger LOGGER = Logger.getLogger(LanguageDaoImpl.class);

    private static LanguageDaoImpl instance;

    private static final String TABLE_LANGUAGE = "language";
    private static final String COLUMN_LANGUAGE_ID_PK = "language_id";
    private static final String COLUMN_LANGUAGE_NAME = "language_name";
    private static final String COLUMN_LANGUAGE_CODE = "language_code";

    private final String SQL_SELECT = "SELECT * FROM " + TABLE_LANGUAGE;

    private static final String SQL_INSERT_LANGUAGE = "INSERT INTO " + TABLE_LANGUAGE
            + " (" + COLUMN_LANGUAGE_NAME + ","
            + COLUMN_LANGUAGE_CODE + ") "
            + "VALUES (?,?)";

    private LanguageDaoImpl(){}

    public static LanguageDaoImpl getInstance(){
        if(instance == null){
            instance = new LanguageDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Language> findAll() throws Exception {
        return findByVaryingParams(SQL_SELECT);
    }

    @Override
    public Language findWhereLanguageIdEquals(Long languageId) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_LANGUAGE_ID_PK + " = ?", languageId));
    }

    @Override
    public Language findWhereLanguageNameEquals(String languageName) throws Exception {
        return fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_LANGUAGE_NAME + " = ?", languageName));
    }

    @Override
    public Language findWhereLanguageCodeEquals(String languageCode) throws Exception {
        return  fetchSingleResult(findByVaryingParams(SQL_SELECT
                + " WHERE " + COLUMN_LANGUAGE_CODE + " = ?", languageCode));
    }

    @Override
    public List<Language> findByVaryingParams(String sql, Object... params) throws Exception {
        ArrayList<Language> result = new ArrayList<>();
        try(Connection  connection = Datasource.getInstance().getConnection();
            PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(sql), params);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                Language language = new Language();
                language.setLanguageId(resultSet.getLong(COLUMN_LANGUAGE_ID_PK));
                language.setLanguageName(resultSet.getString(COLUMN_LANGUAGE_NAME));
                language.setLanguageCode(resultSet.getString(COLUMN_LANGUAGE_CODE));
                result.add(language);
            }
        }catch (SQLException e){
            LOGGER.error("Exception in findByVaryingParams method of LanguageDaoImpl class.");
            throw new SQLException();
        }
        return result;
    }

    @Override
    public void insertNewLanguage(Language language) throws Exception {
        try (Connection connection = Datasource.getInstance().getConnection();
             PreparedStatement statement = PreparedStatementBuilder.setValues(connection.prepareStatement(SQL_INSERT_LANGUAGE),
                     language.getLanguageName(),language.getLanguageCode())){
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in insertNewLanguage method of LanguageDaoImpl class.");
            throw new SQLException();
        }
    }

    private Language fetchSingleResult(List<Language> languages) {
        if(languages.size() > 0){
            return languages.remove(0);
        }
        return null;
    }
}
