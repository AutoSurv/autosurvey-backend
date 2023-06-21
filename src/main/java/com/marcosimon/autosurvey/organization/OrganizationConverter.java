package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyService;
import com.marcosimon.autosurvey.countrygroup.CountryConverter;
import com.marcosimon.autosurvey.models.AutoSurveyResponseDTO;
import com.marcosimon.autosurvey.models.CountryResponseDTO;
import com.marcosimon.autosurvey.models.OrgCountryDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationConverter {

    public static OrganizationResponseDTO toResponseDto(Organization organization) {
        List<OrgCountryDTO> dtos = organization.getCountries().stream().map(CountryConverter::toResponseDto).toList();
        return new OrganizationResponseDTO(organization.getOrgId(), organization.getOrgName(), dtos);
    }

}
