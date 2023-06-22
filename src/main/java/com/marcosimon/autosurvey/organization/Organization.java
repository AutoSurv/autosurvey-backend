package com.marcosimon.autosurvey.organization;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Document
public class Organization {
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    private String orgId;
    private String orgName;
    private List<String> surveys;

    public Organization() {
    }

    public Organization(String orgId, String orgName, List<String> surveys) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.surveys = surveys;
    }
    public Organization(String orgName) {
        this.orgName = orgName;
        this.surveys = new ArrayList<>();
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
}
