package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.organization.Organization;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Document(collection = "users")
public class UserModel {

    @MongoId(value = FieldType.OBJECT_ID)
    private String userId;
    private String username;
    private String password;
    private String email;
    private String roles;
    @DocumentReference(lazy = true)
    private Organization organization;

    public UserModel() {
    }

    public UserModel(String userId, String username, String password, String email, String roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
