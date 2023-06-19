package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrganizationResponseDTO(
        @JsonProperty("org_id")
        String orgId,
        @JsonProperty("org_name")
        String orgName,
        @JsonProperty
        List<CountryResponseDTO>  countries


        ) {
}
