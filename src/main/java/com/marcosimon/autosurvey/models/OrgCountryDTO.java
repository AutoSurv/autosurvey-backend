package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrgCountryDTO( @JsonProperty String countryId,  @JsonProperty String country,
                             @JsonProperty String orgId, @JsonProperty String orgName) {
}
