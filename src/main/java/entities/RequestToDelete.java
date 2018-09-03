package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class hold user request to delete some activity.
 *
 * @author Mirosha
 */
public class RequestToDelete implements Serializable {

    private Long deleteId;
    private boolean isActive;
    private Long assignId;
    private Long userId;

    public RequestToDelete() {
    }

    public Long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Long deleteId) {
        this.deleteId = deleteId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getAssignId() {
        return assignId;
    }

    public void setAssignId(Long assignId) {
        this.assignId = assignId;
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
        RequestToDelete that = (RequestToDelete) o;
        return isActive == that.isActive &&
                Objects.equals(deleteId, that.deleteId) &&
                Objects.equals(assignId, that.assignId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteId, isActive, assignId, userId);
    }

    @Override
    public String toString() {
        return "RequestToDelete{" +
                "deleteId=" + deleteId +
                ", isActive=" + isActive +
                ", assignId=" + assignId +
                ", userId=" + userId +
                '}';
    }
}
