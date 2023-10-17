package com.marcosimon.autosurvey.models;

public record NewCurrencyInfoDTO(
        String currency,
        Float exchangeRate,
        String date,
        String countryName,
        String year
) {
}
