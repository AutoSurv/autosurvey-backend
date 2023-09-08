package com.marcosimon.autosurvey.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
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

    private List<String> surveysIds;
    private List<String> usersIds;

    public Organization() {
    }

    public Organization(String orgId, String orgName, List<String> surveysId) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.surveysIds = surveysId;
    }
    public Organization(String orgName, String creatorId) {
        this.orgName = orgName;
        this.surveysIds = new ArrayList<>();
        this.usersIds = new ArrayList<>();
        this.usersIds.add(creatorId);
    }

    public Organization(String orgId, String orgName, String userId) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.surveysIds = new ArrayList<>();
        this.usersIds = new ArrayList<>();
        this.usersIds.add(userId);
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

    public List<String> getSurveysIds() {
        return surveysIds;
    }

    public void setSurveysIds(List<String> surveysId) {
        this.surveysIds = surveysId;
    }

    public List<String> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<String> usersId) {
        this.usersIds = usersId;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", surveys=" + surveysIds +
                ", users=" + usersIds +
                '}';
    }

}
