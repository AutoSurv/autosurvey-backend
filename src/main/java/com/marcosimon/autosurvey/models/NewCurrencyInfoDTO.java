package com.marcosimon.autosurvey.models;

public record CurrencyInfoDTO(

        //CurrencyInfoDTO currInfo,
        String currencyCountryId,
        String currency,
        Float exchangeRate
) {
}
