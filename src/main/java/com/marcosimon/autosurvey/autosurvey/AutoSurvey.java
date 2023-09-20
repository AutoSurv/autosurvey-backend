package com.marcosimon.autosurvey.autosurvey;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AutoSurvey {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String country;
    private Long year;
    private Long rent;
    private Long utilities;
    private Long food;
    private Long basicItems;
    private Long transportation;
    private Long educationTotal;
    private Long educationSupplies;
    private Long educationFee;
    private String educationType;
    private String accommodationType;
    private String profession;
    private String locationGiven;
    private String locationClustered;
    private Integer numResidents;
    private Integer numIncomes;
    private Integer numFullIncomes;
    private Integer numChildren;
    private Long totalIncome;
    private String comments;
    private String orgId;
    private String orgName;
    private String userId;

    public AutoSurvey(String country, Long year, Long rent, Long utilities, Long food, Long basicItems,
                      Long transportation, Long educationTotal, Long educationSupplies, Long educationFee,
                      String educationType, String accommodationType, String profession, String locationGiven,
                      String locationClustered, Integer numResidents, Integer numIncomes, Integer numFullIncomes,
                      Integer numChildren, Long totalIncome, String comments, String orgId, String orgName, String userId) {
        this.country = country;
        this.year = year;
        this.rent = rent;
        this.utilities = utilities;
        this.food = food;
        this.basicItems = basicItems;
        this.transportation = transportation;
        this.educationTotal = educationTotal;
        this.educationSupplies = educationSupplies;
        this.educationFee = educationFee;
        this.educationType = educationType;
        this.accommodationType = accommodationType;
        this.profession = profession;
        this.locationGiven = locationGiven;
        this.locationClustered = locationClustered;
        this.numResidents = numResidents;
        this.numIncomes = numIncomes;
        this.numFullIncomes = numFullIncomes;
        this.numChildren = numChildren;
        this.totalIncome = totalIncome;
        this.comments = comments;
        this.orgId = orgId;
        this.orgName = orgName;
        this.userId = userId;
    }

}
