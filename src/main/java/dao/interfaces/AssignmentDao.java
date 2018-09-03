package dao.interfaces;

import entities.Assignment;

import java.util.List;

/**
 * @author Mirosha
 */
public interface AssignmentDao {

    /**
     * Method which gets all Assignment items from Database.
     *
     * @return List of Assignment items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Assignment> findAll() throws Exception;

    /**
     * Method which gets Assignment item with specified parameter from Database.
     *
     * @param assignId The specified assignId of item in Database.
     * @param isActive The specified active parameter of item in Database.
     * @return Assignment item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    Assignment findWhereAssignIdAndIsActiveEquals(Long assignId, boolean isActive) throws Exception;

    /**
     * Method which gets Assignment item with specified parameter from Database.
     *
     * @param email       The specified email parameter of item in Database.
     * @param description The specified description parameter of item in Database.
     * @param isActive    The specified active parameter of item in Database.
     * @return Assignment item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    Assignment findWhereEmailDescriptionActiveEquals(String email, String description, boolean isActive) throws Exception;

    /**
     * Method which gets all Assignment items from Database.
     *
     * @param userEmail The specified email parameter of item in Database.
     * @param isActive  The specified active parameter of item in Database.
     * @return List of Assignment items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Assignment> findWhereUserEmailAndActiveEquals(String userEmail, boolean isActive) throws Exception;

    /**
     * Method which gets all Assignment items from Database.
     *
     * @param isActive The specified active parameter of item in Database.
     * @return List of Assignment items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Assignment> findWhereActiveEquals(boolean isActive) throws Exception;

    /**
     * Method which gets all Assignment items for current page of representation from Database.
     *
     * @param isActive       The specified active parameter of item in Database.
     * @param currentPage    Number of current page of representation items.
     * @param recordsPerPage Max number of records per page.
     * @return List of Assignment items per page.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Assignment> findAssignmentsByLimitWhereIsActive(boolean isActive, int currentPage, int recordsPerPage) throws Exception;

    /**
     * Method which gets all Assignment items of User for current page of representation from Database.
     *
     * @param email          The specified email parameter of item in Database.
     * @param isActive       The specified active parameter of item in Database.
     * @param currentPage    Number of current page of representation items.
     * @param recordsPerPage Max number of records per page.
     * @return List of Assignment items per page.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Assignment> findAssignmentsByLimitForUser(String email, boolean isActive, int currentPage, int recordsPerPage) throws Exception;

    /**
     * Method which gets all Assignment items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of Assignment items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Assignment> findByVaryingParams(String sql, Object... params) throws Exception;

    /**
     * Method which gets number of all items in Database.
     *
     * @param email    Specified email parameter of items.
     * @param isActive Specified active parameter of items.
     * @return Number of rows of items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberForUser(String email, boolean isActive) throws Exception;

    /**
     * Method which gets number of all items in Database.
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
     * Inserts new Assignment item into Database.
     *
     * @param assignment Item to add into Database.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void insertNewAssignment(Assignment assignment) throws Exception;

    /**
     * Method which updates state of Assignment item in Database.
     *
     * @param assignId  Specified assignId parameter of item.
     * @param totalTime Specified totalTime parameter of item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void updateAssignmentTotalTime(Long assignId, Long totalTime) throws Exception;

    /**
     * Method which updates state of Assignment item in Database.
     *
     * @param email       Specified email parameter of item.
     * @param description Specified description parameter of item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void setInactive(String email, String description) throws Exception;
}
