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
 * @author Mirosha
 */
public class UserType implements Serializable {

    private Long userTypeId;
    private String typeName;

    public UserType() {
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return Objects.equals(userTypeId, userType.userTypeId) &&
                Objects.equals(typeName, userType.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userTypeId, typeName);
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
