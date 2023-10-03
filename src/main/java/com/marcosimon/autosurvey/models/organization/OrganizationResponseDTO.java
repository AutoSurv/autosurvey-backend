package com.marcosimon.autosurvey.models.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrganizationResponseDTO(
        @JsonProperty("orgId")
        String orgId,
        @JsonProperty("orgName")
        String orgName,
        @JsonProperty
        List<String>  surveysIds,
        @JsonProperty
        List<String>  usersIds

) {
}
