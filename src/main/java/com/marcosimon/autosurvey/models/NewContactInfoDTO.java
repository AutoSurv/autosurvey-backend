package com.marcosimon.autosurvey.models;

public record NewContactInfoDTO(
        //String contactId,
        String contactPerson,
        String contactPhone,
        String contactEmail,
        String contactJobTitle,
        String orgName,
        String countryName,
        String date

) {
}
