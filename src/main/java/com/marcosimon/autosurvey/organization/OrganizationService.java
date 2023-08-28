package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.user.UserDbRepository;
import com.marcosimon.autosurvey.user.UserModel;
import com.marcosimon.autosurvey.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    AutoSurveyRepository autoSurveyRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserDbRepository userRepository;

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
        UserModel userModel = userService.getUserById(userId);
        if(org.getUsers().contains(userModel)) {
            return org;
        }
        List<UserModel> userModels = org.getUsers();
        userModels.add(userModel);
        org.setUsers(userModels);
        userModel.setOrganization(org);
        userRepository.save(userModel);
        return organizationRepository.saveOrganization(org);
    }

    public Organization deleteUser(String orgId, String userId) {
        Organization org = organizationRepository.getById(orgId);
        UserModel userModel = userService.getUserById(userId);
        List<UserModel> userModels = org.getUsers();
        List<UserModel> filteredUser = userModels.stream().filter(user -> !user.getUserId().equals(userId)).collect(Collectors.toList());
        org.setUsers(filteredUser);
        userModel.setOrganization(null);
        userRepository.save(userModel);
        return organizationRepository.saveOrganization(org);
    }
}
