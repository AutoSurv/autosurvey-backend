package com.marcosimon.autosurvey.testutils;

import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestData {
  public OrgSurveyDTO setupSurveyDTO;
  public final String testCountry = "Test Country";
  public final String testOrganizationId = "651176a534067a39d9ec6d4b";
  public final String testUserId = "6500b2bdb967834d719fe886";
  public final String wrongTestUserId = "6500b2bdb967834d719fe887";
  public final String wrongSurveyId = "64fb0ba096acb91cc7d09f8b";

  public final String surveyId = "64fb0ba096acb91cc7d09f8a";
  public final String country = "Norway";

  public final CreateSurveyDTO testDTO = new CreateSurveyDTO(testCountry, 0L, 0L, 0L, 0L, 0L,
          0L, 0L, 0L,
          0L, "", "", "", "", "",
          0, 0, 0, 0, 0L, "", testOrganizationId,
          "", testUserId);
  public  final CreateSurveyDTO wrongTestDTO = new CreateSurveyDTO(testCountry, 0L, 0L, 0L, 0L, 0L,
          0L, 0L, 0L,
          0L, "", "", "", "", "",
          0, 0, 0, 0, 0L, "", testOrganizationId,
          "", wrongTestUserId);

  public  final CreateSurveyDTO illegalTestDTO = new CreateSurveyDTO(testCountry, 0L, 0L, 0L, 0L, 0L,
          0L, 0L, 0L,
          0L, "", "", "", "", "",
          0, 0, 0, 0, 0L, "", testOrganizationId,
          "", null);

  public  final CreateSurveyDTO updatedTestDTO = new CreateSurveyDTO(testCountry, 2000L, 0L, 0L, 0L, 0L,
          0L, 0L, 0L,
          0L, "", "", "", "", "",
          0, 0, 0, 0, 0L, "", testOrganizationId,
          "", testUserId);


}
