package com.marcosimon.autosurvey.models;

import java.util.List;

public record AutoSurveyListDto(
        List<AutoSurveyResponseDTO> surveyDTOList
) {
}
