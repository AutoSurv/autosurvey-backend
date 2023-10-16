package com.marcosimon.autosurvey.models;

public record NewFunctionSalaryInfoDTO(
        String functionCustomName,
        Integer basicSalary,
        Integer monthlyAllowance,
        Integer TGC,
        Integer level,
        String functionName,
        String orgName,
        String countryName,
        String date
) {
}
