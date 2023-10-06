package com.marcosimon.autosurvey.models;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;

import java.util.Date;

public record NewMsfOrgInfoDTO(

        //MsfOrgInfoDTO orgInfo,
        String orgId,
        String orgName,
        String orgFullName,
        Integer workingHours,
        Integer thirteenthSalary,
        String currencyInUse,
        CountryInfo countryInfo

) {
}
