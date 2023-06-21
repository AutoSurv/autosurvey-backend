package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    AutoSurveyRepository autoSurveyRepository;


    public OrganizationService() {
    }

    public OrganizationService(OrganizationRepository organizationRepository, AutoSurveyRepository autoSurveyRepository) {
        this.organizationRepository = organizationRepository;
        this.autoSurveyRepository = autoSurveyRepository;
    }


    public List<OrganizationResponseDTO> getAllOrganizations() {
        return organizationRepository.listOrganizations().stream().map(org -> {
            return OrganizationConverter.toResponseDto(org, autoSurveyRepository.getSurveyByIds(org.getSurveys()));
        }).toList();
    }

    public OrganizationResponseDTO getOrgById(String id) {
        Organization org = organizationRepository.getById(id);
        System.out.println(org.getOrgName());
        return OrganizationConverter.toResponseDto(org, autoSurveyRepository.getSurveyByIds(org.getSurveys()));
    }


    public OrganizationResponseDTO addOrganization(Organization org) {
        Organization existingOrg = organizationRepository.getByOrgName(org.getOrgName());
        if(existingOrg == null) {
            return OrganizationConverter.toResponseDto(organizationRepository.saveOrganization(org), autoSurveyRepository.getSurveyByIds(org.getSurveys()));
        }
        return null;
    }

    public OrganizationResponseDTO renameOrganization(String id, String name) {
        Organization org = organizationRepository.getById(id);
        Organization existingOrg = organizationRepository.getByOrgName(name);
        if (existingOrg == null) {
            org.setOrgName(name);
            return OrganizationConverter.toResponseDto(organizationRepository.saveOrganization(org), autoSurveyRepository.getSurveyByIds(org.getSurveys()));
        }
        return  null;
    }

    public void deleteOrganization(String orgId) {
        List<String> surveyIds = organizationRepository.getById(orgId).getSurveys();
        for(String id : surveyIds) {
            AutoSurvey survey = autoSurveyRepository.getById(id);
            if(survey != null) {
                survey.setOrgName("None");
                survey.setOrgId("deleted");
                autoSurveyRepository.saveSurvey(survey);
            }
        }
        organizationRepository.deleteOrganization(orgId);
    }

    public void deleteOrgByName(String name) {
        List<String> surveyIds = organizationRepository.getByOrgName(name).getSurveys();
        for(String id : surveyIds) {
            AutoSurvey survey = autoSurveyRepository.getById(id);
            if(survey != null) {
                survey.setOrgName("None");
                survey.setOrgId("deleted");
                autoSurveyRepository.saveSurvey(survey);
            }
        }
        organizationRepository.deleteOrgByName(name);
    }

}
