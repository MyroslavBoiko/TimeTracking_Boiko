package dao.interfaces;

import entities.UserType;

import java.util.List;

public interface UserTypeDao {
    List<UserType> findAll() throws Exception;

    UserType findWhereUserTypeIdEquals(Long userTypeId) throws Exception;

    UserType findWhereTypeNameEquals(String typeName) throws Exception;

    List<UserType> findByVaryingParams(final String sql, Object... params) throws Exception;

    List<UserType> findUserTypesByLimit(int currentPage, int recordsPerPage) throws Exception;

    int getNumberOfRows() throws Exception;

    void insertNewType(UserType userType) throws Exception;
}
