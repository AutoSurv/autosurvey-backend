package com.marcosimon.autosurvey.models;

public record NewBenchmarkInfoDTO(
        //CountryInfoDTO countryInfo,

        String benchmarkInfoId,
        String countryName,
        String countryIso,
        String continent,
        String benchmarkStartDate,
        String benchmarkEndDate,
        String benchmarkReferenceCurrency,
        Float benchmarkSelectedDbWorkingHours,
        Float averageWorkingHours

) {
}
