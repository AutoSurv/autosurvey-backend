package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AutoSurveyServiceTest {

  @Autowired
  AutoSurveyService service;
  @Test
  void getCorrectAllSurveysSize() {

    int allSurveysLength = 119;
    int result = service.getAllSurveys().size();
    assertEquals(allSurveysLength, result);

  }

  @Test
  void getWrongAllSurveysSize() {

    int allSurveysLength = 80;
    int result = service.getAllSurveys().size();
    assertNotEquals(allSurveysLength, result);

  }

  @Test
  void getCorrectSurveyById() {
    String surveyId = "64fb0ba096acb91cc7d09f8a";
    String country = "Norway";
    OrgSurveyDTO surveyDto = service.getSurveyById(surveyId);
    assertEquals(country, surveyDto.country());
  }

  @Test
  void getWrongSurveyById() {
    String surveyId = "64fb0ba096acb91cc7d09f8b";
    CustomException exception =
    assertThrows(CustomException.class, () -> {
      service.getSurveyById(surveyId);
    });

    String expectedMessage = "Survey is not in DB";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void addSurvey() {
  }

  @Test
  void updateSurveyData() {
  }

  @Test
  void deleteSurvey() {
  }
}