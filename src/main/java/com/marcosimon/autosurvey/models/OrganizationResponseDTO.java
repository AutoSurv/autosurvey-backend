package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrganizationResponseDTO(
        @JsonProperty("orgId")
        String orgId,
        @JsonProperty("orgName")
        String orgName,
        @JsonProperty
        List<OrgSurveyDTO>  surveys


        ) {
}
