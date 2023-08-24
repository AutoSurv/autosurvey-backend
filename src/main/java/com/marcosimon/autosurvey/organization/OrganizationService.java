package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.user.UserModel;
import com.marcosimon.autosurvey.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    AutoSurveyRepository autoSurveyRepository;
    @Autowired
    UserService userService;

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
        return OrganizationConverter.toResponseDto(org, autoSurveyRepository.getSurveyByIds(org.getSurveys()));
    }


    public OrganizationResponseDTO addOrganization(Organization org) {

        Organization existingOrg = organizationRepository.getByOrgName(org.getOrgName());
        if(existingOrg == null) {
            //add creator to org
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
        Organization org = organizationRepository.getById(orgId);
        org.getSurveys().forEach(i -> autoSurveyRepository.deleteSurvey(i));
        organizationRepository.deleteOrganization(orgId);
    }

    public void deleteOrgByName(String name) {

        organizationRepository.deleteOrgByName(name);
    }

    public Organization addUser(String orgId, String userId) {
        Organization org = organizationRepository.getById(orgId);
        System.out.println("org: " + org);
        UserModel userModel = userService.getUserById(userId);
        System.out.println("userModel: " + userModel);
        List<UserModel> userModels = org.getUsers();
        userModels.add(userModel);
        org.setUsers(userModels);
        Organization updatedOrg = organizationRepository.saveOrganization(org);

        return updatedOrg;
    }
}
