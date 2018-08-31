package dao.interfaces;

import entities.Activity;

import java.util.List;

public interface ActivityDao {
    List<Activity> findAll() throws Exception;

    Activity findWhereActivityIdEquals(Long ActivityId) throws Exception;

    Activity findWhereDescriptionEquals(String description) throws Exception;

    List<Activity> findByVaryingParams(String sql, Object... params) throws Exception;

    List<Activity> findActivitiesByLimit(int currentPage, int recordsPerPage) throws Exception;

    int getNumberOfRows() throws Exception;

    int getNumberOfRowsByParams(String sql, Object... params) throws Exception;

    void insertNewActivity(Activity activity) throws Exception;

}
