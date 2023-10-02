package com.marcosimon.autosurvey.models;

import java.util.Date;

public record NewCountryInfo(
        //CountryInfoDTO countryInfo,
        String countryId,
        String countryName,
        Date date,
        String currencyRef
) {
}
