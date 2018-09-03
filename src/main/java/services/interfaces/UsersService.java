package services.interfaces;

import entities.User;

import java.util.List;

public interface UsersService extends Service {
    List<User> getUsersPerPage(int currentPage, int recordsPerPage);
    int getCountOfRows();
}
