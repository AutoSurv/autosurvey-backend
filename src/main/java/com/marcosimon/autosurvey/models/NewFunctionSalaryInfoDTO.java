package com.marcosimon.autosurvey.models;

public record NewFunctionSalaryInfoDTO(
        String orgFunctionId,
        String orgFunction,
        Integer basicSalary,
        Integer allowancePerFunction,
        Integer TGC,
        String functionId,
        String orgName,
        String countryName,
        String year
) {
}
