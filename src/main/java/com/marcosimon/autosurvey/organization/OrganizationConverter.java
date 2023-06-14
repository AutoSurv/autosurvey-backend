package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyService;
import com.marcosimon.autosurvey.models.AutoSurveyDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationConverter {

    @Autowired
    static
    AutoSurveyService surveyService;
    public static OrganizationResponseDTO toDTO(Organization org, List<AutoSurvey> surveys) {
        List<AutoSurveyDTO> dtos = surveys.stream()
                .map(survey -> surveyService.convertToDto(survey)).toList();
        return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(), dtos);
    }

}
