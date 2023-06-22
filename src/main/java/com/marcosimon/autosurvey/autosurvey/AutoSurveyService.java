package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AutoSurveyService {

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  AutoSurveyRepository autoSurveyRepository;

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

  public OrgSurveyDTO updateSurveyData(CreateSurveyDTO newSurveyData) {
    AutoSurvey storedSurvey = autoSurveyRepository.getById(newSurveyData.orgId());
    if (storedSurvey == null) return null;

    if (newSurveyData.country() != null) {
      storedSurvey.setCountry(newSurveyData.country());
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

    if (newSurveyData.educationType() != null) {
      storedSurvey.setEducationType(newSurveyData.educationType());
    }

    if (newSurveyData.accommodationType() != null) {
      storedSurvey.setAccommodationType(newSurveyData.accommodationType());
    }

    if (newSurveyData.profession() != null) {
      storedSurvey.setProfession(newSurveyData.profession());
    }

    if (newSurveyData.locationGiven() != null) {
      storedSurvey.setLocationGiven(newSurveyData.locationGiven());
    }

    if (newSurveyData.locationClustered() != null) {
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

    if (newSurveyData.comments() != null) {
      storedSurvey.setComments(newSurveyData.comments());
    }
    return SurveyConverter.toResponseDto(autoSurveyRepository.saveSurvey(storedSurvey));
  }

  public void deleteSurvey(String id) {
    autoSurveyRepository.deleteSurvey(id);
  }
}
