package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrganizationDTO(
        @JsonProperty
        String orgName,

        @JsonProperty
        String country
) {
}
