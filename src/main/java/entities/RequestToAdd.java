/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mirosha
 */
public class RequestToAdd implements Serializable {

    private Long addId;
    private boolean isActive;
    private Long activityId;
    private Long userId;

    public RequestToAdd() {
    }

    public Long getAddId() {
        return addId;
    }

    public void setAddId(Long addId) {
        this.addId = addId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestToAdd that = (RequestToAdd) o;
        return isActive == that.isActive &&
                Objects.equals(addId, that.addId) &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addId, isActive, activityId, userId);
    }

    @Override
    public String toString() {
        return "RequestToAdd{" +
                "addId=" + addId +
                ", isActive=" + isActive +
                ", activityId=" + activityId +
                ", userId=" + userId +
                '}';
    }
}
