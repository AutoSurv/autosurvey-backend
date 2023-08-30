package com.marcosimon.autosurvey.autosurvey;

import org.modelmapper.ModelMapper;
import com.marcosimon.autosurvey.models.AutoSurveyListResDTO;
import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;


@Service
public class AutoSurveyService {

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  AutoSurveyRepository autoSurveyRepository;

  ModelMapper mapper = new ModelMapper();

  public AutoSurveyService() {
  }

  public AutoSurveyService(OrganizationRepository organizationRepository, AutoSurveyRepository autoSurveyRepository) {
    this.organizationRepository = organizationRepository;
    this.autoSurveyRepository = autoSurveyRepository;
  }


  public List<OrgSurveyDTO> getAllSurveys() {

    return autoSurveyRepository.listSurveys().stream().map(SurveyConverter::toResponseDto).toList();
  }

  public OrgSurveyDTO getSurveyById(String id) {
    AutoSurvey gotSurvey = autoSurveyRepository.getById(id);
    if (gotSurvey == null) return null;

    return SurveyConverter.toResponseDto(gotSurvey);
  }

  public OrgSurveyDTO addSurvey(CreateSurveyDTO dto) {

    Organization org = organizationRepository.getById(dto.organization().getOrgId());

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
            dto.organization());
    AutoSurvey newSurvey = autoSurveyRepository.saveSurvey(survey);
    List<AutoSurvey> orgToSurvey = org.getSurveys();
    orgToSurvey.add(newSurvey);
    org.setSurveys(orgToSurvey);
    Organization organization = organizationRepository.saveOrganization(org);
    newSurvey.setOrganization(organization);
    AutoSurvey updateNewSurvey = autoSurveyRepository.saveSurvey(newSurvey);
    return SurveyConverter.toResponseDto(updateNewSurvey);

  }

  public OrgSurveyDTO updateSurveyData(String id, CreateSurveyDTO newSurveyData) {

    if (newSurveyData == null) return null;

    AutoSurvey storedSurvey = autoSurveyRepository.getById(id);

    if (storedSurvey == null) return null;

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
    return SurveyConverter.toResponseDto(autoSurveyRepository.saveSurvey(storedSurvey));
  }

  public void deleteSurvey(String id) {
    AutoSurvey surveyToDelete = autoSurveyRepository.getById(id);
    Organization org = organizationRepository.getById(surveyToDelete.getOrganization().getOrgId());
    List<AutoSurvey> surveyList = org.getSurveys();

    List<AutoSurvey> newList = surveyList.stream().filter(survey -> {
      boolean b = !Objects.equals(survey.getId(), surveyToDelete.getId());
      return b;
    }).toList();

    org.setSurveys(newList);
    organizationRepository.saveOrganization(org);
    autoSurveyRepository.deleteSurvey(id);
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
