package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCountryDTO(@JsonProperty("country") String country, @JsonProperty String orgId, @JsonProperty String orgName) {
}
