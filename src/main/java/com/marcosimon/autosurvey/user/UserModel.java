package com.marcosimon.autosurvey.user;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class UserModel {

    @MongoId(value = FieldType.OBJECT_ID)
    private String userId;
    private String username;
    private String password;
    private String email;
    private String roles;
    private String status;
    private String orgId;
    private List<String> surveysIds;

    public UserModel() {
    }

    public UserModel(String userId, String username, String password, String email, String roles, String status, String orgId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.status = status;
        this.orgId = orgId;
        this.surveysIds = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<String> getSurveysIds() {
        return surveysIds;
    }

    public void setSurveysIds(List<String> surveysIds) {
        this.surveysIds = surveysIds;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", status='" + status + '\'' +
                ", organization=" + orgId +
                ", surveys=" + surveysIds +
                '}';
    }
}
