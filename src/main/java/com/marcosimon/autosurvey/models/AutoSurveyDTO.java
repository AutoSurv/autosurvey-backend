package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutoSurveyDTO(
        @JsonProperty("id")
        String id,
        @JsonProperty("country")
        String country,
        @JsonProperty("rent")
        long rent,
        @JsonProperty("utilities")
        long utilities,
        @JsonProperty("food")
        long food,
        @JsonProperty("basic_items")
        long basicItems,
        @JsonProperty("transportation")
        long transportation,
        @JsonProperty("education_total")
        long educationTotal,
        @JsonProperty("education_supplies")
        long educationSupplies,
        @JsonProperty("education_fee")
        long educationFee,
        @JsonProperty("education_type")
        String educationType,
        @JsonProperty("accommodation_type")
        String accommodationType,
        @JsonProperty("profession")
        String profession,
        @JsonProperty("location_given")
        String locationGiven,
        @JsonProperty("location_clustered")
        String locationClustered,
        @JsonProperty("num_residents")
        int numResidents,
        @JsonProperty("num_incomes")
        int numIncomes,
        @JsonProperty("num_full_incomes")
        int numFullIncomes,
        @JsonProperty("num_children")
        int numChildren,
        @JsonProperty("total_income")
        long totalIncome,
        @JsonProperty("comments")
        String comments
) {
}
