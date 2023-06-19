package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.AutoSurveyResponseDTO;
import com.marcosimon.autosurvey.models.CreateSurveyDTO;

public class SurveyConverter {

   public static AutoSurvey fromDto(CreateSurveyDTO dto) {
        return new AutoSurvey(
                dto.country(),
                dto.rent(),
                dto.utilities(),
                dto.food(),
                dto.basicItems(),
                dto.transportation(),
                dto.educationTotal(),
                dto.educationSupplies(),
                dto.educationFee(),
                dto.educationType(),
                dto.accommodationType(),
                dto.profession(),
                dto.locationGiven(),
                dto.locationClustered(),
                dto.numResidents(),
                dto.numIncomes(),
                dto.numFullIncomes(),
                dto.numChildren(),
                dto.totalIncome(),
                dto.comments());
    }

    public static AutoSurvey fromResDto(AutoSurveyResponseDTO dto) {
        return new AutoSurvey(
                dto.country(),
                dto.rent(),
                dto.utilities(),
                dto.food(),
                dto.basicItems(),
                dto.transportation(),
                dto.educationTotal(),
                dto.educationSupplies(),
                dto.educationFee(),
                dto.educationType(),
                dto.accommodationType(),
                dto.profession(),
                dto.locationGiven(),
                dto.locationClustered(),
                dto.numResidents(),
                dto.numIncomes(),
                dto.numFullIncomes(),
                dto.numChildren(),
                dto.totalIncome(),
                dto.comments());
    }

   public static AutoSurveyResponseDTO toResponseDto(AutoSurvey autoSurvey) {
        return new AutoSurveyResponseDTO(autoSurvey.getId(),
                autoSurvey.getCountry(),
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
                autoSurvey.getComments());
    }
}
