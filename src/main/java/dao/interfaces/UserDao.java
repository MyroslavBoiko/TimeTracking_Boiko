package dao.interfaces;

import datasource.Datasource;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    List<User> findAll() throws Exception;

    User findWhereUserIdEquals(Long userId) throws Exception;

    User findWhereEmailEquals(String email) throws Exception;

    List<User> findWhereUserTypeIdEquals(Long userTypeId) throws Exception;

    List<User> findByVaryingParams(String sql, Object... params) throws Exception;

    List<User> findUsersByLimit(int currentPage, int recordsPerPage) throws Exception;

    int getNumberOfRows() throws Exception;

    int getNumberOfRowsByParams(String sql, Object... params) throws Exception;

    void insertNewUser(User user) throws Exception;

}
