package dao.interfaces;

import entities.RequestToDelete;

import java.util.List;

/**
 * DAO interface to work with requests to delete
 * instances of database.
 *
 * @author Mirosha
 */
public interface RequestToDeleteDao {

    /**
     * Method which gets all RequestToDelete items from Database.
     *
     * @return List of RequestToDelete items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToDelete> findAll() throws Exception;

    /**
     * Method which gets all RequestToDelete items with specified parameter from Database.
     *
     * @param isActive The specified active parameter of items in Database.
     * @return List of RequestToDelete items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToDelete> findWhereActiveEquals(boolean isActive) throws Exception;

    /**
     * Method which gets all RequestToDelete items for current page of representation from Database.
     *
     * @param isActive       The specified active parameter of items in Database.
     * @param currentPage    Number of current page of representation items.
     * @param recordsPerPage Max number of records per page.
     * @return List of RequestToDelete items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToDelete> findRequestsToDeleteIsActiveByLimit(boolean isActive, int currentPage, int recordsPerPage) throws Exception;

    /**
     * Method which gets all RequestToDelete items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of RequestToDelete items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<RequestToDelete> findByVaryingParams(String sql, Object... params) throws Exception;

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
     * Method which updates state of RequestRoDelete item in Database.
     *
     * @param assignId Specified Id of item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void setInactive(Long assignId) throws Exception;

    /**
     * Inserts new RequestToDelete item into Database.
     *
     * @param requestToDelete Item to add into Database.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void insertNewRequestToDelete(RequestToDelete requestToDelete) throws Exception;

}
