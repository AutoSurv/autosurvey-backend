package com.marcosimon.autosurvey.models;

public record NewCountryInfoDTO(
        //CountryInfoDTO countryInfo,
        String countryName,
        String isoCode,
        String year,
        String currencyRef
) {
}
