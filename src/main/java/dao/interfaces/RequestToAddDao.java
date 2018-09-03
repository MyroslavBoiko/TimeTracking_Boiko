package dao.interfaces;

import entities.RequestToAdd;

import java.util.List;

/**
 * @author Mirosha
 */
public interface RequestToAddDao {

    /**
     * Method which gets all RequestToAdd items from Database.
     *
     * @return List of RequestToAdd items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToAdd> findAll() throws Exception;

    /**
     * Method which gets RequestToAdd item with specified parameter from Database.
     *
     * @param activityId The specified activityId of item in Database.
     * @param userId     The specified userId of item in Database.
     * @param isActive   The specified active parameter of item in Database.
     * @return RequestToAdd item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    RequestToAdd findWhereActivityIdAndUserIdEquals(Long activityId, Long userId, boolean isActive) throws Exception;

    /**
     * Method which gets all RequestToAdd items with specified parameter from Database.
     *
     * @param isActive The specified active parameter of item in Database.
     * @return RequestToAdd items with specified parameter.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToAdd> findWhereActiveEquals(boolean isActive) throws Exception;

    /**
     * Method which gets all RequestToAdd items for current page of representation from Database.
     *
     * @param isActive       The specified active parameter of items in Database.
     * @param currentPage    Number of current page of representation items.
     * @param recordsPerPage Max number of records per page.
     * @return List of RequestToAdd items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToAdd> findRequestsToAddIsActiveByLimit(boolean isActive, int currentPage, int recordsPerPage) throws Exception;

    /**
     * Method which gets all RequestToAdd items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of RequestToAdd items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToAdd> findByVaryingParams(String sql, Object... params) throws Exception;

    /**
     * Method which gets number of all items in Database.
     *
     * @return Number of rows of items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberOfRows() throws Exception;

    /**
     * Method which gets number of items by parameter in Database.
     *
     * @param isActive Specified active parameter of items.
     * @return Number of rows of items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberByActive(boolean isActive) throws Exception;

    /**
     * Number of rows with specified parameters.
     *
     * @param sql    SQL statement which are used to work with Database.
     * @param params List of parameters to use by SQL.
     * @return Number of specified rows.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberOfRowsByParams(String sql, Object... params) throws Exception;

    /**
     * Method which updates state of RequestToAdd item in Database.
     *
     * @param activityId Specified activityId of item.
     * @param userId     Specified userId of item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void setInactiveRequestToAdd(Long activityId, Long userId) throws Exception;

    /**
     * Inserts new RequestToAdd item into Database.
     *
     * @param requestToAdd Item to add into Database.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void insertNewRequestToAdd(RequestToAdd requestToAdd) throws Exception;

}
