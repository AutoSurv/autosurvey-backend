package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.user.UserDbRepository;
import com.marcosimon.autosurvey.user.UserModel;
import com.marcosimon.autosurvey.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
            return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveys(), org.getUsers());
        }).toList();
    }

    public OrganizationResponseDTO getOrgById(String id) {
        Organization org = organizationRepository.getById(id);
        return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveys(), org.getUsers());
    }


    public OrganizationResponseDTO addOrganization(Organization org) {

        Organization existingOrg = organizationRepository.getByOrgName(org.getOrgName());
        if(existingOrg == null) {
            //add creator to org
            organizationRepository.saveOrganization(org);
            return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveys(), org.getUsers());
        }
        return null;
    }

    public OrganizationResponseDTO renameOrganization(String id, String name) {
        Organization org = organizationRepository.getById(id);
        Organization existingOrg = organizationRepository.getByOrgName(name);
        if (existingOrg == null) {
            org.setOrgName(name);
            Organization renamedOrg = organizationRepository.saveOrganization(org);
            return new OrganizationResponseDTO(renamedOrg.getOrgId(), renamedOrg.getOrgName(), renamedOrg.getSurveys(), renamedOrg.getUsers());
        }
        return  null;
    }

    public void deleteOrganization(String orgId) {
        Organization org = organizationRepository.getById(orgId);
        org.getSurveys().forEach(i -> autoSurveyRepository.deleteSurvey(i.getId()));
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
