package com.marcosimon.autosurvey.models;

public record NewFinalOrgInfoDTO(
        String countryName,
        String date,
        String orgName,
        Integer level,
        String functionName,
        String functionCustomName) {
}
