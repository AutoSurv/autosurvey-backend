package com.marcosimon.autosurvey.models;

public record NewAllowanceValueDTO(
        //AllowanceValueDTO allValue
        Integer colAllowance,
        Integer transportation,
        Integer housing,
        Integer other,
        Integer total,
        String orgName,
        String countryName,
        String date
) {
}
