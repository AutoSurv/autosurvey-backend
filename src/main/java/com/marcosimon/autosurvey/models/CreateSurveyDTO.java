package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateSurveyDTO(
                              @JsonProperty("country")
                              String country,
                              @JsonProperty("rent")
                              long rent,
                              @JsonProperty("utilities")
                              long utilities,
                              @JsonProperty("food")
                              long food,
                              @JsonProperty("basicItems")
                              long basicItems,
                              @JsonProperty("transportation")
                              long transportation,
                              @JsonProperty("educationTotal")
                              long educationTotal,
                              @JsonProperty("educationSupplies")
                              long educationSupplies,
                              @JsonProperty("educationFee")
                              long educationFee,
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
                              int numResidents,
                              @JsonProperty("numIncomes")
                              int numIncomes,
                              @JsonProperty("numFullIncomes")
                              int numFullIncomes,
                              @JsonProperty("numChildren")
                              int numChildren,
                              @JsonProperty("totalIncome")
                              long totalIncome,
                              @JsonProperty("comments")
                              String comments,
                              @JsonProperty("orgId")
                              String orgId

                              ) {
}
