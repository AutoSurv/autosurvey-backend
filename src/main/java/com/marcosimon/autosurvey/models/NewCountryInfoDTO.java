package com.marcosimon.autosurvey.models;

import java.util.Date;

public record NewCountryInfoDTO(
        //CountryInfoDTO countryInfo,
        Long countryId,
        String countryName,
        String date,
        String currencyRef
) {
}
