package dao.interfaces;

import entities.ActivityTranslate;

import java.util.List;

/**
 * DAO interface to work with activity translation
 * instances of database.
 *
 * @author Mirosha
 */
public interface ActivityTranslateDao {

    /**
     * Method which gets all ActivityTranslate items from Database.
     *
     * @return List of ActivityTranslate items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<ActivityTranslate> findAll() throws Exception;

    /**
     * Method which gets ActivityTranslate item with specified parameter from Database.
     *
     * @param description The specified description parameter of item in Database.
     * @return ActivityTranslate item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    ActivityTranslate findWhereDescriptionEquals(String description) throws Exception;

    /**
     * Method which gets ActivityTranslate item with specified parameter from Database.
     *
     * @param translateId The specified translateId parameter of item in Database.
     * @param languageId  The specified languageId parameter of item in Database.
     * @return ActivityTranslate item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    ActivityTranslate findWhereActivityIdAndLanguageIdEquals(Long translateId, Long languageId) throws Exception;

    /**
     * Method which gets all ActivityTranslate items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of ActivityTranslate items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<ActivityTranslate> findByVaryingParams(String sql, Object... params) throws Exception;

    /**
     * Method which gets number of all items in Database.
     *
     * @return Number of rows of items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberOfRows() throws Exception;

    /**
     * Number of rows with specified parameters.
     *
     * @param sql    SQL statement which are used to work with Database.
     * @param params List of parameters to use by SQL.
     * @return Number of specified rows.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberOfRowsByParams(String sql, Object... params) throws Exception;

}
