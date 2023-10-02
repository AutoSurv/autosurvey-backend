package com.marcosimon.autosurvey.models;

public record NewFunctionInfoDTO(
        //FunctionInfoDTO funcInfo,
        String functionId,
        String functionOrgInfoId,
        Integer level,
        String function,
        String functionRef
) {
}
