package com.marcosimon.autosurvey.models;

public record NewAllowancePercentageDTO(
        //AllowancePercentageDTO allPerc,
        Float colAllowance,
        Float transportation,
        Float housing,
        Float other,
        Float total,
        String orgName,
        String countryName,
        String date
) {
}
