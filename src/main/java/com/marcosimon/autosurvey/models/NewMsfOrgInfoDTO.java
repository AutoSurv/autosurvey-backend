package com.marcosimon.autosurvey.models;

public record NewMsfOrgInfoDTO(
        String orgFullName,
        String orgShortName,
        String orgType,
        String dataCollectionDate,
        Integer workingHours,
        Integer thirteenthSalary,
        String currencyInUse,
        String countryName,
        String year

) {
}
