package dao.interfaces;

import entities.Language;

import java.util.List;

/**
 * @author Mirosha
 */
public interface LanguageDao {

    /**
     * Method which gets all Language items from Database.
     *
     * @return List of Language items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Language> findAll() throws Exception;

    /**
     * Method which gets RequestToAdd item with specified parameter from Database.
     *
     * @param languageCode The specified parameter of item in Database.
     * @return Language item.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    Language findWhereLanguageCodeEquals(String languageCode) throws Exception;

    /**
     * Method which gets all Language items with varying list of parameters from Database.
     *
     * @param sql    SQL statement for working with Database
     * @param params List of parameters which are used to find items in Database
     * @return List of Language items.
     * @throws Exception Throws an exception which may occur while working with Database.
     */
    List<Language> findByVaryingParams(String sql, Object... params) throws Exception;

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
