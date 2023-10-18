package com.marcosimon.autosurvey.models;

public record NewAllowanceValueDTO(
        Integer transportation,
        Integer housing,
        Integer colAllowance,
        Integer communication,
        Integer food,
        Integer holiday,
        Integer religious,
        Integer endOfYear,
        Integer medical,
        Integer family,
        Integer education,
        Integer hardship,
        Integer danger,
        Integer location,
        Integer other,
        Integer total,
        String orgName,
        String countryName,
        String year
) {
}
