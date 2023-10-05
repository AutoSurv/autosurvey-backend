package com.marcosimon.autosurvey.models;

import java.util.Date;

public record NewMsfOrgInfoDTO(

        //MsfOrgInfoDTO orgInfo,
        String orgId,
        String orgName,
        String orgFullName,
        Integer workingHours,
        Integer thirteenthSalary,
        String currencyInUse,
        String countryInfoName

) {
}
