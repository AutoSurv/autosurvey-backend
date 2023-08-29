package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.user.UserModel;

import java.util.List;

public record OrganizationResponseDTO(
        @JsonProperty("orgId")
        String orgId,
        @JsonProperty("orgName")
        String orgName,
        @JsonProperty
        List<AutoSurvey>  surveys,
        @JsonProperty
        List<UserModel>  users

) {
}
