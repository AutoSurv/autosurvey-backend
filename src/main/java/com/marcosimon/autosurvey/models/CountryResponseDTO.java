package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CountryResponseDTO(@JsonProperty String countryId, @JsonProperty String country,
                                 @JsonProperty List<AutoSurveyResponseDTO> surveys) {
}
