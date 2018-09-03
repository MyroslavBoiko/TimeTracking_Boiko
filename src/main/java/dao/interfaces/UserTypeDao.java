package dao.interfaces;

import entities.UserType;

import java.util.List;

/**
 *@author Mirosha
 */

public interface UserTypeDao {

    /**
     * Method which gets all UserType items from Database.
     * @return List of UserType items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<UserType> findAll() throws Exception;

    /**
     * Method which gets UserType item with specified parameter from Database.
     * @param userTypeId The specified Id of item in Database.
     * @return UserType item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    UserType findWhereUserTypeIdEquals(Long userTypeId) throws Exception;

    /**
     * Method which gets UserType item with specified parameter from Database.
     * @param typeName Specified name of UserType item in Database.
     * @return UserType item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    UserType findWhereTypeNameEquals(String typeName) throws Exception;

    /**
     * Method which gets all UserType items with varying list of parameters from Database.
     * @param sql SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of UserType items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<UserType> findByVaryingParams(final String sql, Object... params) throws Exception;

    /**
     * Method which gets number of all items in Database.
     * @return Number of rows of items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberOfRows() throws Exception;

    /**
     * Number of rows with specified parameters.
     * @param sql SQL statement which are used to work with Database.
     * @param params List of parameters to use by SQL.
     * @return Number of specified rows.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    int getNumberOfRowsByParams(String sql, Object... params) throws Exception;
}
