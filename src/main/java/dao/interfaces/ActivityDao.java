package dao.interfaces;

import entities.Activity;

import java.util.List;

/**
 * DAO interface to work with activities
 * instances of database.
 *
 * @author Mirosha
 */
public interface ActivityDao {

    /**
     * Method which gets all Activity items from Database.
     *
     * @return List of Activity items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Activity> findAll() throws Exception;

    /**
     * Method which gets Activity item with specified parameter from Database.
     *
     * @param activityId The specified activityId parameter of item in Database.
     * @return Activity item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    Activity findWhereActivityIdEquals(Long activityId) throws Exception;

    /**
     * Method which gets Activity item with specified parameter from Database.
     *
     * @param description The specified description parameter of item in Database.
     * @return Activity item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    Activity findWhereDescriptionEquals(String description) throws Exception;

    /**
     * Method which gets all Activity items for current page of representation from Database.
     *
     * @param currentPage    Number of current page of representation items.
     * @param recordsPerPage Max number of records per page.
     * @return List of Activity items per page.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Activity> findActivitiesByLimit(int currentPage, int recordsPerPage) throws Exception;

    /**
     * Method which gets all Activity items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of Activity items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Activity> findByVaryingParams(String sql, Object... params) throws Exception;

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
