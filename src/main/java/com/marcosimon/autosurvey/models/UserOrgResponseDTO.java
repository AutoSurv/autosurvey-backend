package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.user.UserModel;

import java.util.List;

public record UserOrgResponseDTO(
                                 @JsonProperty("userId")
                                 String userId,
                                 @JsonProperty("username")
                                 String username,
                                 @JsonProperty
                                 String email,
                                 @JsonProperty
                                 String roles,
                                 @JsonProperty
                                 Organization organization) {
}
