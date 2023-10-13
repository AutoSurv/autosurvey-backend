package com.marcosimon.autosurvey.models;

public record NewFunctionSalaryInfoDTO(
        Long functionSalaryId,
        String functionCustomName,
        Integer basicSalary,
        Integer monthlyAllowance,
        Integer TGC,
        Long functionId,
        Long orgId
) {
}
