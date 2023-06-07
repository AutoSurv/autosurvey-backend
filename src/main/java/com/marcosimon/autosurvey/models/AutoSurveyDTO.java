package com.marcosimon.autosurvey.models;

public record AutoSurveyDTO(
        String id,
        String country,
        long rent,
        long utilities,
        long food,
        long basicItems,
        long transportation,
        long educationTotal,
        long educationSupplies,
        long educationFee,
        boolean educationType,
        long accommodationType,
        String profession,
        String locationGiven,
        String locationClustered,
        int numResidents,
        int numIncomes,
        int numFullIncomes,
        int numChildren,
        long totalIncome,
        String comments
) {
}
