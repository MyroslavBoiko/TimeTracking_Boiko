package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class which holds the translation for specified
 * activity and used for localization of
 * represented information.
 *
 * @author Mirosha
 */
public class ActivityTranslate implements Serializable {

    private Long translateId;
    private String description;
    private Long activityId;
    private Long languageId;

    public ActivityTranslate() {
    }

    public Long getTranslateId() {
        return translateId;
    }

    public void setTranslateId(Long translateId) {
        this.translateId = translateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTranslate that = (ActivityTranslate) o;
        return Objects.equals(translateId, that.translateId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(languageId, that.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translateId, description, activityId, languageId);
    }

    @Override
    public String toString() {
        return "ActivityTranslate{" +
                "translateId=" + translateId +
                ", description='" + description + '\'' +
                ", activityId=" + activityId +
                ", languageId=" + languageId +
                '}';
    }
}
