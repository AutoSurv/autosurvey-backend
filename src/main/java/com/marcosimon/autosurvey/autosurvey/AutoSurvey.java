package com.marcosimon.autosurvey.autosurvey;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class AutoSurvey {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    @NonNull
    private String country;
    @NonNull
    private Long year;
    @NonNull
    private Long rent;
    @NonNull
    private Long utilities;
    @NonNull
    private Long food;
    @NonNull
    private Long basicItems;
    @NonNull
    private Long transportation;
    @NonNull
    private Long educationTotal;
    @NonNull
    private Long educationSupplies;
    @NonNull
    private Long educationFee;
    @NonNull
    private String educationType;
    @NonNull
    private String accommodationType;
    @NonNull
    private String profession;
    @NonNull
    private String locationGiven;
    @NonNull
    private String locationClustered;
    @NonNull
    private Integer numResidents;
    @NonNull
    private Integer numIncomes;
    @NonNull
    private Integer numFullIncomes;
    @NonNull
    private Integer numChildren;
    @NonNull
    private Long totalIncome;
    @NonNull
    private String comments;
    @NonNull
    private String orgId;
    @NonNull
    private String orgName;
    @NonNull
    private String userId;

}
