package dao.interfaces;

import entities.Language;

import java.util.List;

public interface LanguageDao {
    List<Language> findAll() throws Exception;
    Language findWhereLanguageIdEquals(Long languageId) throws Exception;
    Language findWhereLanguageNameEquals(String languageName) throws Exception;
    Language findWhereLanguageCodeEquals(String languageCode) throws Exception;
    List<Language> findByVaryingParams(String sql, Object... params) throws Exception;
    void insertNewLanguage(Language language) throws Exception;

}
