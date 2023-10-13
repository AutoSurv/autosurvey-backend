package com.marcosimon.autosurvey.models;

public record NewCurrencyInfoDTO(

        //CurrencyInfoDTO currInfo,
        //String currencyId,
        String currency,
        Float exchangeRate,
        String countryName,
        String date
) {
}
