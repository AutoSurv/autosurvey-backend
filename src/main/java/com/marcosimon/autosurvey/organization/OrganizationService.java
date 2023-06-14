package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.models.CreateOrganizationDTO;
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

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.listOrganizations();
    }

    public Organization getOrgById(String id) {
        return organizationRepository.getById(id);
    }

    public Organization saveOrganization(Organization org) {
        return organizationRepository.saveOrganization(org);
    }

    public OrganizationResponseDTO addOrganization(Organization org) {
        Organization existingOrg = organizationRepository.getByOrgName(org.getOrgName());
        if(existingOrg == null) {
            Organization saveOrg = organizationRepository.saveOrganization(org);

            return OrganizationConverter.toDTO(saveOrg, autoSurveyRepository.get);
        }
    }





}
