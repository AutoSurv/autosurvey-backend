package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.AutoSurveyDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AutoSurveyService {

  @Autowired
  AutoSurveyRepository autoSurveyRepository;


  public AutoSurveyDTO convertToDto (AutoSurvey autoSurvey) {
    AutoSurveyDTO autoSurveyDTO = new AutoSurveyDTO(
            autoSurvey.getId(),
            autoSurvey.getCountry(),
            autoSurvey.getRent(),
            autoSurvey.getUtilities(),
            autoSurvey.getFood(),
            autoSurvey.getBasicItems(),
            autoSurvey.getTransportation(),
            autoSurvey.getEducationTotal(),
            autoSurvey.getEducationSupplies(),
            autoSurvey.getEducationFee(),
            autoSurvey.isEducationType(),
            autoSurvey.getAccommodationType(),
            autoSurvey.getProfession(),
            autoSurvey.getLocationGiven(),
            autoSurvey.getLocationClustered(),
            autoSurvey.getNumResidents(),
            autoSurvey.getNumIncomes(),
            autoSurvey.getNumFullIncomes(),
            autoSurvey.getNumChildren(),
            autoSurvey.getTotalIncome(),
            autoSurvey.getComments()
    );

    return autoSurveyDTO;
  }
  public AutoSurvey convertFromDto (AutoSurveyDTO dto) {
    AutoSurvey autoSurvey = new AutoSurvey(
            dto.id(),
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
            dto.comments()
    );
    return  autoSurvey;
  }


  public List<AutoSurveyDTO> convertToDtoList (List<AutoSurvey> autoSurveyList) {
    List<AutoSurveyDTO> autoSurveyDTOList = new ArrayList<>();

    for (AutoSurvey survey : autoSurveyList) {
      autoSurveyDTOList.add(convertToDto(survey));
    }

    return autoSurveyDTOList;
  }


  public List<AutoSurveyDTO> getAllSurvey() {
    List<AutoSurvey> autoSurveyList = autoSurveyRepository.listSurveys();

    return convertToDtoList(autoSurveyList);
  }


  public AutoSurveyDTO getSurveyById(String id) {
    AutoSurvey survey = autoSurveyRepository.getById(id);
    if (survey == null) {
      return null;
    }

    AutoSurveyDTO autoSurveyDTO = convertToDto(survey);

    return autoSurveyDTO;
  }

  public AutoSurvey saveSurvey(AutoSurveyDTO surveyDTO) {

    return autoSurveyRepository.saveSurvey(convertFromDto(surveyDTO));

  }

  public void saveSurveys(List<AutoSurveyDTO> dtos) {
    autoSurveyRepository.saveSurveys(dtos.stream().map(this::convertFromDto).toList());
  }





}
