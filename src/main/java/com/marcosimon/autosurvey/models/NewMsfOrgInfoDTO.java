package com.marcosimon.autosurvey.models;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;

import java.util.Date;

public record NewMsfOrgInfoDTO(

        String orgFullName,
        String orgName,
        Integer workingHours,
        Integer thirteenthSalary,
        String currencyInUse,
        String countryName,
        String date

) {
}
