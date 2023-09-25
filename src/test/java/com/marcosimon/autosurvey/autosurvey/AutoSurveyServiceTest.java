package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.testutils.TestData;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AutoSurveyServiceTest {

  @Autowired
  AutoSurveyService service;

  @BeforeAll
  void setUp(){
    TestData.setupSurveyDTO = service.addSurvey(TestData.testDTO);
  }

  @AfterAll
  void tearDown(){
    service.deleteSurvey(TestData.setupSurveyDTO.id());
  }

  @Test
  @Order(1)
  void getCorrectAllSurveysSize() {

    int zeroLength = 0;
    int actualResult = service.getAllSurveys().size();
    assertNotEquals(zeroLength, actualResult);

  }

  @Test
  @Order(2)
  void getWrongAllSurveysSize() {

    int allSurveysLength = 80;
    int result = service.getAllSurveys().size();
    assertNotEquals(allSurveysLength, result);

  }

  @Test
  @Order(3)
  void getCorrectSurveyById() {

    OrgSurveyDTO surveyDto = service.getSurveyById(TestData.surveyId);
    assertEquals(TestData.country, surveyDto.country());
  }

  @Test
  @Order(4)
  void getWrongSurveyById() {
    CustomException exception =
    assertThrows(CustomException.class, () -> {
      service.getSurveyById(TestData.wrongSurveyId);
    });

    String expectedMessage = "Survey is not in DB";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  @Order(5)
  void shouldAddAndDeleteSurvey() {

    OrgSurveyDTO createdSurveyDTO = service.addSurvey(TestData.testDTO);
    assertNotNull(createdSurveyDTO.id());
    assertEquals(TestData.testCountry, createdSurveyDTO.country());

    int surveyQuantityBeforeDeletion = service.getAllSurveys().size();
    service.deleteSurvey(createdSurveyDTO.id());
    int surveyQuantityAfterDeletion = service.getAllSurveys().size();

    assertNotEquals(surveyQuantityAfterDeletion, surveyQuantityBeforeDeletion);

  }

  @Test
  @Order(6)
  void wrongUserIdThrowExceptionOnCreatingSurvey() {

    CustomException exception =
            assertThrows(CustomException.class, () -> {
              service.addSurvey(TestData.wrongTestDTO);
            });

    String expectedMessage = "User ID not exist";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  @Order(7)
  void illegalArgumentExceptionCatchDueToUserIdNull() {

    CustomException exception =
            assertThrows(CustomException.class, () -> {
              service.addSurvey(TestData.illegalTestDTO);
            });

    String expectedMessage = "User ID not exist";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  @Order(8)
  void updateSurveyData() {
    OrgSurveyDTO updatedSurveyDTO = service.updateSurveyData(TestData.setupSurveyDTO.id(), TestData.updatedTestDTO);
    Long expectedRent = updatedSurveyDTO.rent();
    Long expectedYear = 2000L;

    assertEquals(expectedYear, updatedSurveyDTO.year());
    assertEquals(expectedRent, updatedSurveyDTO.rent());
  }

  @Test
  @Order(9)
  void customExceptionThrownWithWrongSurveyIdWhenUpdatingSurvey() {
    CustomException exception =
            assertThrows(CustomException.class, () -> {
              service.updateSurveyData(TestData.wrongSurveyId, TestData.updatedTestDTO);
            });

    String expectedMessage = "Survey is not in DB";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  @Order(10)
  void customExceptionThrownWithWrongSurveyIdWhenDeleteSurvey() {

    CustomException exception =
            assertThrows(CustomException.class, () -> {
              service.updateSurveyData(TestData.wrongSurveyId, TestData.updatedTestDTO);
            });

    String expectedMessage = "Survey is not in DB";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

}