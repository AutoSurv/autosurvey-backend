package com.marcosimon.autosurvey.models;

public record NewAllowancePercentageDTO(
        //AllowancePercentageDTO allPerc,
        Float COLA,
        Float transportation,
        Float housing,
        Float other,
        Float total,
        String orgName,
        String countryName,
        String date
) {
}
