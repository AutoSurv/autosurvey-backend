package com.marcosimon.autosurvey.models;

public record NewFunctionSalaryInfoDTO(
        String functionSalaryId,
        String functionCustomName,
        Integer basicSalary,
        Integer monthlyAllowance,
        Integer TGC,
        String functionId,
        String orgId
) {
}
