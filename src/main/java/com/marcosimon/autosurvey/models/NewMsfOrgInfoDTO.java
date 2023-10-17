package com.marcosimon.autosurvey.models;

public record NewMsfOrgInfoDTO(
        String orgFullName,
        String orgName,
        String orgType,
        String dataCollectionDate,
        Integer workingHours,
        Integer thirteenthSalary,
        String currencyInUse,
        String countryName,
        String year

) {
}
