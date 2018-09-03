package services.interfaces;

import entities.User;

import java.util.List;

/**
 * @author Mirosha
 */
public interface UsersService extends Service {

    /**
     * List of users for current page.
     *
     * @param currentPage Number of current page.
     * @param recordsPerPage Maximum number of records per page.
     * @return List of users.
     */
    List<User> getUsersPerPage(int currentPage, int recordsPerPage);

    /**
     * Number of used rows
     *
     * @return Number of records
     */
    int getCountOfRows();
}
