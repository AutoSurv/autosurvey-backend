package com.marcosimon.autosurvey.autosurvey;


import com.marcosimon.autosurvey.models.OrgSurveyDTO;

public class SurveyConverter {

    public static OrgSurveyDTO toResponseDto(AutoSurvey autoSurvey) {
        return new OrgSurveyDTO(
                autoSurvey.getId(),
                autoSurvey.getCountry(),
                autoSurvey.getYear(),
                autoSurvey.getRent(),
                autoSurvey.getUtilities(),
                autoSurvey.getFood(),
                autoSurvey.getBasicItems(),
                autoSurvey.getTransportation(),
                autoSurvey.getEducationTotal(),
                autoSurvey.getEducationSupplies(),
                autoSurvey.getEducationFee(),
                autoSurvey.getEducationType(),
                autoSurvey.getAccommodationType(),
                autoSurvey.getProfession(),
                autoSurvey.getLocationGiven(),
                autoSurvey.getLocationClustered(),
                autoSurvey.getNumResidents(),
                autoSurvey.getNumIncomes(),
                autoSurvey.getNumFullIncomes(),
                autoSurvey.getNumChildren(),
                autoSurvey.getTotalIncome(),
                autoSurvey.getComments(),
                autoSurvey.getOrgId(),
                autoSurvey.getOrgName());
    }
}
