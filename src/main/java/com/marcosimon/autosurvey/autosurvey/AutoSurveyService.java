package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.user.UserDbRepository;
import com.marcosimon.autosurvey.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import com.marcosimon.autosurvey.models.AutoSurveyListResDTO;
import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AutoSurveyService {

  private final OrganizationRepository organizationRepository;

  private final AutoSurveyRepository autoSurveyRepository;

  private final UserDbRepository userDbRepository;

  ModelMapper mapper = new ModelMapper();

  public List<OrgSurveyDTO> getAllSurveys() {
    return autoSurveyRepository.listSurveys().stream().map(SurveyConverter::toResponseDto).toList();
  }

  public OrgSurveyDTO getSurveyById(String id) {

    Optional.of(!ObjectId.isValid(id)).ifPresent((v) -> {
      if (v) throw new CustomException(INVALID_PARAMETER);
    });

    /*
    if (id.equals("") || !ObjectId.isValid(id)) {
      throw new CustomException(INVALID_PARAMETER);
    }
    */

    AutoSurvey gotSurvey = autoSurveyRepository.getById(id);
    if (gotSurvey == null) {
      throw new CustomException(SAVED_SURVEY_NOT_FOUND);
    }

    return SurveyConverter.toResponseDto(gotSurvey);
  }
  @Transactional
  public synchronized OrgSurveyDTO addSurvey(CreateSurveyDTO dto) {
    UserModel user;
    try {
      user = userDbRepository
              .findById(dto.userId())
              .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    } catch (IllegalArgumentException iae) {
      throw new CustomException(USER_NOT_FOUND);
    }


    AutoSurvey survey = new AutoSurvey(
              dto.country(),
              dto.year(),
              dto.rent(),
              dto.utilities(),
              dto.food(),
              dto.basicItems(),
              dto.transportation(),
              dto.educationTotal(),
              dto.educationSupplies(),
              dto.educationFee(),
              dto.educationType(),
              dto.accommodationType(),
              dto.profession(),
              dto.locationGiven(),
              dto.locationClustered(),
              dto.numResidents(),
              dto.numIncomes(),
              dto.numFullIncomes(),
              dto.numChildren(),
              dto.totalIncome(),
              dto.comments(),
              dto.orgId(),
              dto.orgName(),
              dto.userId());

    AutoSurvey newSurvey = autoSurveyRepository.saveSurvey(survey);

    Organization org = organizationRepository.getById(dto.orgId());
    if (org == null) {
      throw new CustomException(ORGANIZATION_NOT_FOUND);
    }

    List<String> orgSurveysIds = org.getSurveysIds();
    orgSurveysIds.add(newSurvey.getId());
    org.setSurveysIds(orgSurveysIds);

    List<String> userSurveysListIds = user.getSurveysIds();
    userSurveysListIds.add(newSurvey.getId());
    user.setSurveysIds(userSurveysListIds);

    Organization organization = organizationRepository.saveOrganization(org);
    newSurvey.setOrgId(organization.getOrgId());
    newSurvey.setUserId(user.getUserId());
    userDbRepository.save(user);
    AutoSurvey updateNewSurvey = autoSurveyRepository.saveSurvey(newSurvey);
    return SurveyConverter.toResponseDto(updateNewSurvey);

  }

  public OrgSurveyDTO updateSurveyData(String id, CreateSurveyDTO newSurveyData) {

    Optional.of(!ObjectId.isValid(id)).ifPresent((v) -> {
      if (v) throw new CustomException(INVALID_PARAMETER);
    });

    if (newSurveyData == null) {
      throw new CustomException(INVALID_PARAMETER);
    }

    AutoSurvey storedSurvey = autoSurveyRepository.getById(id);

    if (storedSurvey == null) {
      throw new CustomException(SAVED_SURVEY_NOT_FOUND);
    }

    if (!Objects.equals(newSurveyData.country(), "")) {
      storedSurvey.setCountry(newSurveyData.country());
    }

    if (newSurveyData.year() != null && newSurveyData.year() > 0) {
      storedSurvey.setYear(newSurveyData.year());
    }

    if (newSurveyData.rent() != null && newSurveyData.rent() >= 0) {
      storedSurvey.setRent(newSurveyData.rent());
    }

    if (newSurveyData.utilities() != null && newSurveyData.utilities() >= 0) {
      storedSurvey.setUtilities(newSurveyData.utilities());
    }

    if (newSurveyData.food() != null && newSurveyData.food() >= 0) {
      storedSurvey.setFood(newSurveyData.food());
    }

    if (newSurveyData.basicItems() != null && newSurveyData.basicItems() >= 0) {
      storedSurvey.setBasicItems(newSurveyData.basicItems());
    }

    if (newSurveyData.transportation() != null && newSurveyData.transportation() >= 0) {
      storedSurvey.setTransportation(newSurveyData.transportation());
    }

    if (newSurveyData.educationTotal() != null && newSurveyData.educationTotal() >= 0) {
      storedSurvey.setEducationTotal(newSurveyData.educationTotal());
    }

    if (newSurveyData.educationSupplies() != null && newSurveyData.educationSupplies() >= 0) {
      storedSurvey.setEducationSupplies(newSurveyData.educationSupplies());
    }

    if (newSurveyData.educationFee() != null && newSurveyData.educationFee() >= 0) {
      storedSurvey.setEducationFee(newSurveyData.educationFee());
    }

    if (!Objects.equals(newSurveyData.educationType(), "")) {
      storedSurvey.setEducationType(newSurveyData.educationType());
    }

    if (!Objects.equals(newSurveyData.accommodationType(), "")) {
      storedSurvey.setAccommodationType(newSurveyData.accommodationType());
    }

    if (!Objects.equals(newSurveyData.profession(), "")) {
      storedSurvey.setProfession(newSurveyData.profession());
    }

    if (!Objects.equals(newSurveyData.locationGiven(), "")) {
      storedSurvey.setLocationGiven(newSurveyData.locationGiven());
    }

    if (!Objects.equals(newSurveyData.locationClustered(), "")) {
      storedSurvey.setLocationClustered(newSurveyData.locationClustered());
    }

    if (newSurveyData.numResidents() != null && newSurveyData.numResidents() >= 0) {
      storedSurvey.setNumResidents(newSurveyData.numResidents());
    }

    if (newSurveyData.numIncomes() != null && newSurveyData.numIncomes() >= 0) {
      storedSurvey.setNumIncomes(newSurveyData.numIncomes());
    }

    if (newSurveyData.numFullIncomes() != null && newSurveyData.numFullIncomes() >= 0) {
      storedSurvey.setNumFullIncomes(newSurveyData.numFullIncomes());
    }

    if (newSurveyData.numChildren() != null && newSurveyData.numChildren() >= 0) {
      storedSurvey.setNumChildren(newSurveyData.numChildren());
    }

    if (newSurveyData.totalIncome() != null && newSurveyData.totalIncome() >= 0) {
      storedSurvey.setTotalIncome(newSurveyData.totalIncome());
    }

    if (!Objects.equals(newSurveyData.comments(), "")) {
      storedSurvey.setComments(newSurveyData.comments());
    }

    if (!Objects.equals(newSurveyData.userId(), storedSurvey.getUserId())) {
      userDbRepository.findById(newSurveyData.userId()).ifPresent(user -> storedSurvey.setUserId(user.getUserId()));
    }

    return SurveyConverter.toResponseDto(autoSurveyRepository.saveSurvey(storedSurvey));
  }

  public String deleteSurvey(String id) {

    Optional.ofNullable(id).orElseThrow(() -> new CustomException(INVALID_PARAMETER));

    if (id.equals("") || !ObjectId.isValid(id)) {
      throw new CustomException(INVALID_PARAMETER);
    }

    AutoSurvey surveyToDelete = autoSurveyRepository.getById(id);
    if (surveyToDelete == null) {
      throw new CustomException(SAVED_SURVEY_NOT_FOUND);
    }
    Organization org = organizationRepository.getById(surveyToDelete.getOrgId());
    if (org == null) {
      throw new CustomException(ORGANIZATION_NOT_FOUND);
    }
    String userId = surveyToDelete.getUserId();

    userDbRepository
            .findById(userId)
            .ifPresentOrElse( userPresent -> {
              userPresent.setSurveysIds(userPresent.getSurveysIds().stream().filter(surveyId ->
                      !Objects.equals(surveyId, surveyToDelete.getId())
              ).toList());
              userDbRepository.save(userPresent);
            }, () -> {
              throw new CustomException(USER_NOT_FOUND);
            } );

    List<String> surveyList = org.getSurveysIds();
    List<String> newList = surveyList.stream().filter(surveyId ->
        !Objects.equals(surveyId, surveyToDelete.getId())
    ).toList();

    org.setSurveysIds(newList);
    organizationRepository.saveOrganization(org);
    autoSurveyRepository.deleteSurvey(id);
    return "204";
  }

  public Page<AutoSurvey> getPaginatedSurveysController(int page, String country) {
    if (country.isEmpty()) {
      return  autoSurveyRepository.getPaginatedSurveys(page);
    }
    return autoSurveyRepository.getSurveysByCountry(page, country);
  }

  public AutoSurveyListResDTO getPaginatedSurveys(int page, String country) {
    Page<AutoSurvey> surveys = getPaginatedSurveysController(page, country);
    AutoSurveyListResDTO dto = mapper.map(surveys, AutoSurveyListResDTO.class);
    dto.setSurveys(surveys.getContent());
    return dto;
  }

}
