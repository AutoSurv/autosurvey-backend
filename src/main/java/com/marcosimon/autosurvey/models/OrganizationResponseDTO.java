package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrganizationResponseDTO(
        @JsonProperty
        String orgId,
        @JsonProperty
        String orgName,
        @JsonProperty
        List<OrgCountryDTO>  countries


        ) {
}
