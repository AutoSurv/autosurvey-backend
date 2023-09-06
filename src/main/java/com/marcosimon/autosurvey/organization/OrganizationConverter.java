package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.models.UserOrgResponseDTO;

import java.util.List;

public class OrganizationConverter {

    public static OrganizationResponseDTO toResponseDto(Organization organization, List<AutoSurvey> surveys) {
        List<OrgSurveyDTO> surveyDTOS = surveys.stream().map(autoSurvey -> {
            return new OrgSurveyDTO(  autoSurvey.getId(),
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
                    autoSurvey.getOrganization(),
                    new UserOrgResponseDTO(autoSurvey.getUserModel().getUserId(), autoSurvey.getUserModel().getUsername(),
                            autoSurvey.getUserModel().getEmail(), autoSurvey.getUserModel().getRoles(),
                            autoSurvey.getUserModel().getStatus()));
        }).toList();
        return new OrganizationResponseDTO(organization.getOrgId(), organization.getOrgName(), surveys, organization.getUsers());
    }

    public static Organization fromDto(OrganizationResponseDTO dto) {
        //List<AutoSurvey> surveys = dto.surveys().stream().map(OrgSurveyDTO::id).toList();
        return new Organization(dto.orgId(), dto.orgName(), dto.surveys());
    }

}
