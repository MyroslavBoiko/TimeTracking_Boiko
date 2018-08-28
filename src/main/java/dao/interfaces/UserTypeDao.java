package dao.interfaces;

import entities.UserType;

import java.util.List;

public interface UserTypeDao {
    List<UserType> findAll() throws Exception;
    UserType findWhereUserTypeIdEquals(Long userTypeId) throws Exception;
    UserType findWhereTypeNameEquals(String typeName) throws Exception;
    List<UserType> findByVaryingParams(final String sql, Object... params) throws Exception;
    void insertNewType(UserType userType) throws Exception;
}
