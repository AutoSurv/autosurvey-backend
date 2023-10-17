package com.marcosimon.autosurvey.models;

public record FinalOrgInfoDTO(
        String countryName,
        String year,
        String orgName,
        String orgFullName,
        String msfLevel,
        String irffgLevel,
        String functionId,
        String functionName,
        String orgFunctionId,
        String orgFunction,
        String currencyRef,
        String currencyInUse,
        String currency,
        Float exchangeRate,
        Integer workingHours,
        Integer thirteenthSalary,
        Integer basicSalary,
        Integer allowancePerFunction,
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
