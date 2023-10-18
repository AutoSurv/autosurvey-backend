package com.marcosimon.autosurvey.models;

public record NewAllowancePercentageDTO(
        Float transportation,
        Float housing,
        Float colAllowance,
        Float communication,
        Float food,
        Float holiday,
        Float religious,
        Float endOfYear,
        Float medical,
        Float family,
        Float education,
        Float hardship,
        Float danger,
        Float location,
        Float other,
        Float total,
        String orgName,
        String countryName,
        String year
) {
}
