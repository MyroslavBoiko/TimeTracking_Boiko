package dao.interfaces;

import entities.User;

import java.util.List;

/**
 * DAO interface to work with user
 * instances of database.
 *
 * @author Mirosha
 */
public interface UserDao {

    /**
     * Method which gets all User items from Database.
     *
     * @return List of User items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<User> findAll() throws Exception;

    /**
     * Method which gets User item with specified parameter from Database.
     *
     * @param userId The specified Id of item in Database.
     * @return User item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    User findWhereUserIdEquals(Long userId) throws Exception;

    /**
     * Method which gets all User items with specified parameter from Database.
     *
     * @param email The specified email parameter of item in Database.
     * @return User item with specified email parameter.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    User findWhereEmailEquals(String email) throws Exception;

    /**
     * Method which gets all User items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of User items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<User> findByVaryingParams(String sql, Object... params) throws Exception;

    /**
     * Method which gets all User items for current page of representation from Database.
     *
     * @param currentPage    Number of current page of representation items.
     * @param recordsPerPage Max number of records per page.
     * @return List of User items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<User> findUsersByLimit(int currentPage, int recordsPerPage) throws Exception;

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

    /**
     * Inserts new User item into Database.
     *
     * @param user Item to add into Database.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    void insertNewUser(User user) throws Exception;

}
