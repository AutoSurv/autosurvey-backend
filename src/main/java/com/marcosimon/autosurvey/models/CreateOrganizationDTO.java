package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrganizationDTO(
        @JsonProperty("org_name")
        String orgName
) {
}
