package com.marcosimon.autosurvey.models;

public record FunctionInfoDTO(
        //FunctionInfoDTO funcInfo,
        String functionId,
        String functionOrgInfoId,
        Integer level,
        String function,
        String functionRef
) {
}
