package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrganizationDTO(
        @JsonProperty("orgName")
        String orgName,
        @JsonProperty("creatorName")
        String creatorName
) {
}
