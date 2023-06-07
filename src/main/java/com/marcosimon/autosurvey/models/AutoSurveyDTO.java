package com.marcosimon.autosurvey.models;

public record AutoSurveyDTO(
        String id,
        int familyMemeber,
        int numIcomes,
        long totalIncome,
        String job,
        String area,
        long rent,
        long bills,
        long transport,
        long food
) {
}
