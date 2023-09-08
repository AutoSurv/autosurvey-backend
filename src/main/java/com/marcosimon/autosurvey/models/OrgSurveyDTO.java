package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcosimon.autosurvey.organization.Organization;

public record OrgSurveyDTO(@JsonProperty String id,
                           @JsonProperty String country,
                           @JsonProperty("year")
                           Long year,
                           @JsonProperty("rent")
                           Long rent,
                           @JsonProperty("utilities")
                           Long utilities,
                           @JsonProperty("food")
                           Long food,
                           @JsonProperty("basicItems")
                           Long basicItems,
                           @JsonProperty("transportation")
                           Long transportation,
                           @JsonProperty("educationTotal")
                           Long educationTotal,
                           @JsonProperty("educationSupplies")
                           Long educationSupplies,
                           @JsonProperty("educationFee")
                           Long educationFee,
                           @JsonProperty("educationType")
                           String educationType,
                           @JsonProperty("accommodationType")
                           String accommodationType,
                           @JsonProperty("profession")
                           String profession,
                           @JsonProperty("locationGiven")
                           String locationGiven,
                           @JsonProperty("locationClustered")
                           String locationClustered,
                           @JsonProperty("numResidents")
                           Integer numResidents,
                           @JsonProperty("numIncomes")
                           Integer numIncomes,
                           @JsonProperty("numFullIncomes")
                           Integer numFullIncomes,
                           @JsonProperty("numChildren")
                           Integer numChildren,
                           @JsonProperty("totalIncome")
                           Long totalIncome,
                           @JsonProperty("comments")
                           String comments,
                           @JsonProperty
                           String orgId,
                           @JsonProperty
                           String userId
                           ) {
}
