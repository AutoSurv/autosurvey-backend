package com.marcosimon.autosurvey.models;

public record NewAllowancePercentageDTO(
        Float colAllowance,
        Float transportation,
        Float housing,
        Float other,
        Float total,
        String orgName,
        String countryName,
        String year
) {
}
