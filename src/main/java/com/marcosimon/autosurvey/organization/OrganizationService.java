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
            return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveysIds(), org.getUsersIds());
        }).toList();
    }

    public OrganizationResponseDTO getOrgById(String id) {
        Organization org = organizationRepository.getById(id);
        return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveysIds(), org.getUsersIds());
    }

    public OrganizationResponseDTO addOrganization(Organization org) {

        Organization existingOrg = organizationRepository.getById(org.getOrgId());
        if(existingOrg == null) {
            Organization newOrg = organizationRepository.saveOrganization(org);
            return new OrganizationResponseDTO(newOrg.getOrgId(), newOrg.getOrgName(),newOrg.getSurveysIds(), newOrg.getUsersIds());
        }
        return null;
    }

    public OrganizationResponseDTO renameOrganization(String id, String name) {
        Organization org = organizationRepository.getById(id);
        Organization existingOrg = organizationRepository.getByOrgName(name);
        if (existingOrg == null) {
            org.setOrgName(name);
            Organization renamedOrg = organizationRepository.saveOrganization(org);
            return new OrganizationResponseDTO(renamedOrg.getOrgId(), renamedOrg.getOrgName(), renamedOrg.getSurveysIds(), renamedOrg.getUsersIds());
        }
        return  null;
    }

    public void deleteOrganization(String orgId) {
        Organization org = organizationRepository.getById(orgId);
        org.getSurveysIds().forEach(i -> autoSurveyRepository.deleteSurvey(i));
        organizationRepository.deleteOrganization(orgId);
    }

    public void deleteOrgByName(String name) {
        organizationRepository.deleteOrgByName(name);
    }

    public Organization addUser(String orgId, String userId) {
        Organization org = organizationRepository.getById(orgId);
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) return null;

        if(org.getUsersIds().contains(userModel.getOrgId())) {
            return org;
        }

        List<String> userIds = org.getUsersIds();
        userIds.add(userModel.getUserId());
        org.setUsersIds(userIds);
        userModel.setOrgId(org.getOrgId());
        userRepository.save(userModel);
        return organizationRepository.saveOrganization(org);
    }

    public Organization deleteUser(String orgId, String userId) {
        Organization org = organizationRepository.getById(orgId);
        UserModel userModel = userService.getUserById(userId);

        List<String> userIds = org.getUsersIds();
        List<String> filteredUserIds = userIds.stream().filter(id -> !id.equals(userId)).collect(Collectors.toList());
        org.setUsersIds(filteredUserIds);
        userModel.setOrgId(null);
        userRepository.save(userModel);
        return organizationRepository.saveOrganization(org);
    }
}
