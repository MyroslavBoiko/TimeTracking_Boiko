package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Class which holds languages codes and names.
 * Used for localization representation.
 *
 * @author Mirosha
 */
public class Language implements Serializable {

    private Long languageId;
    private String languageName;
    private String languageCode;

    public Language() {
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(languageId, language.languageId) &&
                Objects.equals(languageName, language.languageName) &&
                Objects.equals(languageCode, language.languageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, languageName, languageCode);
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }
}
