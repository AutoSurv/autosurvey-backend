package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.AutoSurveyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AutoSurveyService {

  @Autowired
  AutoSurveyRepository autoSurveyRepository;


  public AutoSurveyDTO convertToDto (AutoSurvey autoSurvey) {
    AutoSurveyDTO autoSurveyDTO = new AutoSurveyDTO(
            autoSurvey.getId(),
            autoSurvey.getFamilyMember(),
            autoSurvey.getNumIncomes(),
            autoSurvey.getTotalIncome(),
            autoSurvey.getJob(),
            autoSurvey.getArea(),
            autoSurvey.getRent(),
            autoSurvey.getBills(),
            autoSurvey.getTransport(),
            autoSurvey.getFood()
    );

    return autoSurveyDTO;
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


  public AutoSurveyDTO getSurvey(String id) {
    AutoSurvey survey = autoSurveyRepository.getById(id);
    if (survey == null) {
      return null;
    }

    AutoSurveyDTO autoSurveyDTO = convertToDto(survey);

    return autoSurveyDTO;
  }







}
