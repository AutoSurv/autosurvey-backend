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

    return SurveyConverter.toResponseDto(autoSurveyRepository.getById(id));
  }

  public OrgSurveyDTO addSurvey(CreateSurveyDTO dto) {

    Organization org = organizationRepository.getById(dto.orgId());

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
            org.getOrgName());
    AutoSurvey newSurvey = autoSurveyRepository.saveSurvey(survey); //to have id
    List<String> orgToSurvey = org.getSurveys();
    orgToSurvey.add(newSurvey.getId());
    org.setSurveys(orgToSurvey);
    organizationRepository.saveOrganization(org);
    return SurveyConverter.toResponseDto(newSurvey);

  }

  public OrgSurveyDTO updateSurveyData(String id, CreateSurveyDTO newSurveyData) {

    if (newSurveyData == null) return null;

    AutoSurvey storedSurvey = autoSurveyRepository.getById(id);
    if (storedSurvey == null) return null;

    if (!Objects.equals(newSurveyData.country(), "")) {
      storedSurvey.setCountry(newSurveyData.country());
    }

    if (newSurveyData.year() > 0) {
      storedSurvey.setYear(newSurveyData.year());
    }

    if (newSurveyData.rent() > 0) {
      storedSurvey.setRent(newSurveyData.rent());
    }

    if (newSurveyData.utilities() > 0) {
      storedSurvey.setUtilities(newSurveyData.utilities());
    }

    if (newSurveyData.food() > 0) {
      storedSurvey.setFood(newSurveyData.food());
    }

    if (newSurveyData.basicItems() > 0) {
      storedSurvey.setBasicItems(newSurveyData.basicItems());
    }

    if (newSurveyData.transportation() > 0) {
      storedSurvey.setTransportation(newSurveyData.transportation());
    }

    if (newSurveyData.educationTotal() > 0) {
      storedSurvey.setEducationTotal(newSurveyData.educationTotal());
    }

    if (newSurveyData.educationSupplies() > 0) {
      storedSurvey.setEducationSupplies(newSurveyData.educationSupplies());
    }

    if (newSurveyData.educationFee() > 0) {
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

    if (newSurveyData.numResidents() > 0) {
      storedSurvey.setNumResidents(newSurveyData.numResidents());
    }

    if (newSurveyData.numIncomes() > 0) {
      storedSurvey.setNumIncomes(newSurveyData.numIncomes());
    }

    if (newSurveyData.numFullIncomes() > 0) {
      storedSurvey.setNumFullIncomes(newSurveyData.numFullIncomes());
    }

    if (newSurveyData.numChildren() > 0) {
      storedSurvey.setNumChildren(newSurveyData.numChildren());
    }

    if (newSurveyData.totalIncome() > 0) {
      storedSurvey.setTotalIncome(newSurveyData.totalIncome());
    }

    if (!Objects.equals(newSurveyData.comments(), "")) {
      storedSurvey.setComments(newSurveyData.comments());
    }
    return SurveyConverter.toResponseDto(autoSurveyRepository.saveSurvey(storedSurvey));
  }

  public void deleteSurvey(String id) {
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
