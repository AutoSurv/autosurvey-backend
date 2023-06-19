package com.marcosimon.autosurvey.countrygroup;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.SurveyConverter;
import com.marcosimon.autosurvey.models.AutoSurveyResponseDTO;
import com.marcosimon.autosurvey.models.CountryResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class CountryConverter {
    public static CountryGroup fromDto(CountryResponseDTO dto) {
        List<AutoSurvey> surveys = dto.surveys().stream().map(SurveyConverter::fromResDto).toList();
        return new CountryGroup(dto.country(),
                                surveys
                                );
    }

    public static CountryResponseDTO toResponseDto(CountryGroup country) {
        if (country.getSurveys() != null) {
            List<AutoSurveyResponseDTO> dtos = country.getSurveys().stream().map(SurveyConverter::toResponseDto).toList();
            return new CountryResponseDTO(country.getCountryId(), country.getCountry(), dtos);
        }
        return new CountryResponseDTO(country.getCountryId(), country.getCountry(), new ArrayList<>());
    }
}
