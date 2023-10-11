package com.marcosimon.autosurvey.models;

public record FinalOrgInfoDTO(
        String countryName,
        String date,
        String orgName,
        String orgFullName,
        Integer level,
        String functionName,
        String functionCustomName,
        String currencyRef,
        String currencyInUse,
        String currency,
        Float exchangeRate,
        Integer workingHours,
        Integer thirteenthSalary,
        Integer basicSalary,
        Integer monthlyAllowance,
        Integer colAllowance,
        Integer transportationAllowance,
        Integer housingAllowance,
        Integer otherAllowance,
        Integer totalAllowance,
        Float colAllowancePercent,
        Float transportationAllowancePercent,
        Float housingAllowancePercent,
        Float otherAllowancePercent,
        Float totalAllowancePercent,
        Integer tgc

) {
}
