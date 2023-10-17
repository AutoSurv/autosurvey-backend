package com.marcosimon.autosurvey.models;

public record NewAllowanceValueDTO(
        Integer colAllowance,
        Integer transportation,
        Integer housing,
        Integer other,
        Integer total,
        String orgName,
        String countryName,
        String year
) {
}
