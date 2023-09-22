package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.constants.ErrorCode;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.user.UserDbRepository;
import com.marcosimon.autosurvey.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.marcosimon.autosurvey.constants.ErrorCode.ALREADY_SAVED_ORGANIZATION;
import static com.marcosimon.autosurvey.constants.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final AutoSurveyRepository autoSurveyRepository;
    private final UserDbRepository userRepository;

    public List<OrganizationResponseDTO> getAllOrganizations() {

        return organizationRepository.listOrganizations().stream().map(org -> {
            return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveysIds(), org.getUsersIds());
        }).toList();
    }

    public OrganizationResponseDTO getOrgById(String id) {
        Organization org = organizationRepository.getById(id);
        if(org == null) {
            throw new CustomException(ErrorCode.SAVED_ORGANIZATION_NOT_FOUND);
        }
        return new OrganizationResponseDTO(org.getOrgId(), org.getOrgName(),org.getSurveysIds(), org.getUsersIds());
    }

    public OrganizationResponseDTO addOrganization(Organization org) {

        Organization existingOrg = organizationRepository.getByOrgName(org.getOrgName());
        if(existingOrg != null) {
            throw new CustomException(ALREADY_SAVED_ORGANIZATION);
        }
        Organization newOrg = organizationRepository.saveOrganization(org);
        return new OrganizationResponseDTO(newOrg.getOrgId(), newOrg.getOrgName(),newOrg.getSurveysIds(), newOrg.getUsersIds());
    }

    public OrganizationResponseDTO renameOrganization(String id, String name) {
        Organization org = organizationRepository.getById(id);
        Organization existingOrg = organizationRepository.getByOrgName(name);
        if (existingOrg != null) {
            throw new CustomException(ALREADY_SAVED_ORGANIZATION);
        }
        org.setOrgName(name);
        Organization renamedOrg = organizationRepository.saveOrganization(org);
        return new OrganizationResponseDTO(renamedOrg.getOrgId(), renamedOrg.getOrgName(), renamedOrg.getSurveysIds(), renamedOrg.getUsersIds());
    }

    public OrgSurveyDTO getOrgSurvey(String surveyId) {
        AutoSurvey survey = autoSurveyRepository.getById(surveyId);
        return new OrgSurveyDTO(survey.getId(), survey.getCountry(),
                survey.getYear(), survey.getRent(),
                survey.getUtilities(), survey.getFood(),
                survey.getBasicItems(), survey.getTransportation(),
                survey.getEducationTotal(), survey.getEducationSupplies(),
                survey.getEducationFee(), survey.getEducationType(),
                survey.getAccommodationType(), survey.getProfession(), survey.getLocationGiven(),
                survey.getLocationClustered(), survey.getNumResidents(), survey.getNumIncomes(),
                survey.getNumFullIncomes(), survey.getNumChildren(), survey.getTotalIncome(),
                survey.getComments(), survey.getOrgId(),survey.getOrgName(), survey.getUserId());
    }

    public List<OrgSurveyDTO> getOrgSurveys(String id) {
        Organization org = organizationRepository.getById(id);
        List<AutoSurvey> surveyList = autoSurveyRepository.listSurveys();
        return surveyList.stream()
                .filter(survey -> survey.getOrgId().equals(org.getOrgId()))
                .map(survey -> new OrgSurveyDTO(survey.getId(), survey.getCountry(),
                                                survey.getYear(), survey.getRent(),
                                                survey.getUtilities(), survey.getFood(),
                                                survey.getBasicItems(), survey.getTransportation(),
                                                survey.getEducationTotal(), survey.getEducationSupplies(),
                                                survey.getEducationFee(), survey.getEducationType(),
                                                survey.getAccommodationType(), survey.getProfession(), survey.getLocationGiven(),
                                                survey.getLocationClustered(), survey.getNumResidents(), survey.getNumIncomes(),
                                                survey.getNumFullIncomes(), survey.getNumChildren(), survey.getTotalIncome(),
                                                survey.getComments(), survey.getOrgId(), survey.getOrgName(), survey.getUserId())).collect(Collectors.toList());

    }

    public void deleteOrganization(String orgId) {
        Organization org = organizationRepository.getById(orgId);
        org.getSurveysIds().forEach(autoSurveyRepository::deleteSurvey);
        organizationRepository.deleteOrganization(orgId);
    }

    public Organization addUser(String orgId, String userId) {
        Organization org = organizationRepository.getById(orgId);
        UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if(org.getUsersIds().contains(userModel.getUserId())) {
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
        List<String> userIds = org.getUsersIds();
        List<String> filteredUserIds = userIds.stream().filter(id -> !id.equals(userId)).collect(Collectors.toList());
        org.setUsersIds(filteredUserIds);

        userRepository.findById(userId).ifPresent(user -> {
            user.setOrgId(null);
            userRepository.save(user);
        });
        return organizationRepository.saveOrganization(org);
    }
}
