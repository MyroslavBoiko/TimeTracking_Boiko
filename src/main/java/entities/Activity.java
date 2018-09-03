/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Activity class holds name of activity,
 * that can be used by client in the program.
 *
 * @author Mirosha
 */
public class Activity implements Serializable {

    private Long activityId;
    private String description;

    public Activity() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(activityId, activity.activityId) &&
                Objects.equals(description, activity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, description);
    }

    @Override
    public String toString() {
        return "Long{" +
                "activityId=" + activityId +
                ", description='" + description + '\'' +
                '}';
    }
}
