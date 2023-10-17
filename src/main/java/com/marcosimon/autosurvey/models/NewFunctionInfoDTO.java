package com.marcosimon.autosurvey.models;

public record NewFunctionInfoDTO(
        String id,
        String msfEntity,
        String msfProfessionalGroup,
        String msfLevel,
        String irffgLevel,
        String msfJobFamilyDep,
        String msfFunction
) {
}
