/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Mirosha
 */

public class Assignment implements Serializable {

    private Long assignId;
    private boolean isActive;
    private Long totalTime;
    private String activityDescription;
    private String userEmail;

    public Assignment() {
    }

    public Long getAssignId() {
        return assignId;
    }

    public void setAssignId(Long assignId) {
        this.assignId = assignId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return isActive == that.isActive &&
                Objects.equals(assignId, that.assignId) &&
                Objects.equals(totalTime, that.totalTime) &&
                Objects.equals(activityDescription, that.activityDescription) &&
                Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignId, isActive, totalTime, activityDescription, userEmail);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignId=" + assignId +
                ", isActive=" + isActive +
                ", totalTime=" + totalTime +
                ", activityDescription=" + activityDescription +
                ", userEmail=" + userEmail +
                '}';
    }
}
