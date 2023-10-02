package com.marcosimon.autosurvey.models;

public record NewCurrencyInfoDTO(

        //CurrencyInfoDTO currInfo,
        String currencyCountryId,
        String currency,
        Float exchangeRate
) {
}
