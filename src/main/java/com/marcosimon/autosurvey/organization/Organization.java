package com.marcosimon.autosurvey.organization;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marcosimon.autosurvey.user.UserModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;



import java.util.ArrayList;
import java.util.List;


@Document
public class Organization {

    @MongoId(value = FieldType.OBJECT_ID)
    private String orgId;
    private String orgName;
    private List<String> surveys;
    @JsonManagedReference
    @DocumentReference
    private List<UserModel> users;

    public Organization() {
    }

    public Organization(String orgId, String orgName, List<String> surveys) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.surveys = surveys;
        this.users = new ArrayList<>();
    }
    public Organization(String orgName, UserModel creatorName) {
        this.orgName = orgName;
        this.surveys = new ArrayList<>();
        this.users = new ArrayList<>();
        this.users.add(creatorName);
    }

    public Organization(String orgId, String orgName, UserModel user) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.surveys = new ArrayList<>();
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<String> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<String> surveys) {
        this.surveys = surveys;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", surveys=" + surveys +
                ", users=" + users +
                '}';
    }
}
