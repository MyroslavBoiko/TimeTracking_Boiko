package dao.interfaces;

import entities.ActivityTranslate;

import java.util.List;

public interface ActivityTranslateDao {
    List<ActivityTranslate> findAll() throws Exception;

    ActivityTranslate findWhereTranslateIdEquals(Long transalteId) throws Exception;

    List<ActivityTranslate> findWhereLanguageIdEquals(Long languageId) throws Exception;

    List<ActivityTranslate> findWhereActivityIdEquals(Long activityId) throws Exception;

    List<ActivityTranslate> findByVaryingParams(String sql, Object... params) throws Exception;

    List<ActivityTranslate> findActivityTranslatesByLimit(int currentPage, int recordsPerPage) throws Exception;

    int getNumberOfRows() throws Exception;

    void insertNewActivityTranslate(ActivityTranslate activityTranslate) throws Exception;

}
