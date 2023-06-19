package com.marcosimon.autosurvey.models;

import com.fasterxml.jackson.annotation.JsonProperty;
public record AddOrgCountryDTO(@JsonProperty("country") String country) {
}
