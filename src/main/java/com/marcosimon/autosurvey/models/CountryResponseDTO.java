package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CountryResponseDTO(@JsonProperty("country_id") String id, @JsonProperty("country") String country,
                                 @JsonProperty List<AutoSurveyResponseDTO> surveys) {
}
