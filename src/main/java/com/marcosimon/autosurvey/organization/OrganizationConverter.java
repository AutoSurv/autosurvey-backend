package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;

import java.util.List;

public class OrganizationConverter {

    public static OrganizationResponseDTO toResponseDto(Organization organization, List<AutoSurvey> surveys) {
        List<OrgSurveyDTO> surveyDTOS = surveys.stream().map(autoSurvey -> {
            return new OrgSurveyDTO(  autoSurvey.getId(),
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
                    autoSurvey.getComments(),
                    autoSurvey.getOrgId(),
                    autoSurvey.getOrgName());
        }).toList();
        return new OrganizationResponseDTO(organization.getOrgId(), organization.getOrgName(), surveyDTOS);
    }

    public static Organization fromDto(OrganizationResponseDTO dto) {
        List<String> surveys = dto.surveys().stream().map(OrgSurveyDTO::id).toList();
        return new Organization(dto.orgId(), dto.orgName(), surveys);
    }

}
