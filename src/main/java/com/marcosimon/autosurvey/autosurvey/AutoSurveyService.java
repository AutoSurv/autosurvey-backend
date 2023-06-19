package com.marcosimon.autosurvey.autosurvey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoSurveyService {

  @Autowired
  AutoSurveyRepository autoSurveyRepository;

  public List<AutoSurvey> getAllSurveys() {
    return autoSurveyRepository.listSurveys();
  }


  public AutoSurvey getSurveyById(String id) {

    return autoSurveyRepository.getById(id);
  }

  public AutoSurvey saveSurvey(AutoSurvey survey) {

    return autoSurveyRepository.saveSurvey(survey);

  }

  public void saveSurveys(List<AutoSurvey> surveys) {
    autoSurveyRepository.saveSurveys(surveys);
  }

  public AutoSurvey updateSurveyData(AutoSurvey newSurveyData) {
    AutoSurvey storedSurvey = getSurveyById(newSurveyData.getId());
    if (storedSurvey == null) return null;

    if (newSurveyData.getCountry() != null) {
      storedSurvey.setCountry(newSurveyData.getCountry());
    }

    if (newSurveyData.getRent() > 0) {
      storedSurvey.setRent(newSurveyData.getRent());
    }

    if (newSurveyData.getUtilities() > 0) {
      storedSurvey.setUtilities(newSurveyData.getUtilities());
    }

    if (newSurveyData.getFood() > 0) {
      storedSurvey.setFood(newSurveyData.getFood());
    }

    if (newSurveyData.getBasicItems() > 0) {
      storedSurvey.setBasicItems(newSurveyData.getBasicItems());
    }

    if (newSurveyData.getTransportation() > 0) {
      storedSurvey.setTransportation(newSurveyData.getTransportation());
    }

    if (newSurveyData.getEducationTotal() > 0) {
      storedSurvey.setEducationTotal(newSurveyData.getEducationTotal());
    }

    if (newSurveyData.getEducationSupplies() > 0) {
      storedSurvey.setEducationSupplies(newSurveyData.getEducationSupplies());
    }

    if (newSurveyData.getEducationFee() > 0) {
      storedSurvey.setEducationFee(newSurveyData.getEducationFee());
    }

    if (newSurveyData.getEducationType() != null) {
      storedSurvey.setEducationType(newSurveyData.getEducationType());
    }

    if (newSurveyData.getAccommodationType() != null) {
      storedSurvey.setAccommodationType(newSurveyData.getAccommodationType());
    }

    if (newSurveyData.getProfession() != null) {
      storedSurvey.setProfession(newSurveyData.getProfession());
    }

    if (newSurveyData.getLocationGiven() != null) {
      storedSurvey.setLocationGiven(newSurveyData.getLocationGiven());
    }

    if (newSurveyData.getLocationClustered() != null) {
      storedSurvey.setLocationClustered(newSurveyData.getLocationClustered());
    }

    if (newSurveyData.getNumResidents() > 0) {
      storedSurvey.setNumResidents(newSurveyData.getNumResidents());
    }

    if (newSurveyData.getNumIncomes() > 0) {
      storedSurvey.setNumIncomes(newSurveyData.getNumIncomes());
    }

    if (newSurveyData.getNumFullIncomes() > 0) {
      storedSurvey.setNumFullIncomes(newSurveyData.getNumFullIncomes());
    }

    if (newSurveyData.getNumChildren() > 0) {
      storedSurvey.setNumChildren(newSurveyData.getNumChildren());
    }

    if (newSurveyData.getTotalIncome() > 0) {
      storedSurvey.setTotalIncome(newSurveyData.getTotalIncome());
    }

    if (newSurveyData.getComments() != null) {
      storedSurvey.setComments(newSurveyData.getComments());
    }
    return autoSurveyRepository.saveSurvey(storedSurvey);

  }
  /*
  public List<AutoSurvey> findByCountry(List<CountryGroup> groups) {
    return autoSurveyRepository.surveysForCountries(groups);
  }

   */
  public void deleteSurvey(String id) {
    autoSurveyRepository.deleteSurvey(id);
  }
}
