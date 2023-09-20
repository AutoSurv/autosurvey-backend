package com.marcosimon.autosurvey.organization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Organization {

    @MongoId(value = FieldType.OBJECT_ID)
    private String orgId;
    private String orgName;
    private List<String> surveysIds;
    private List<String> usersIds;

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
}
