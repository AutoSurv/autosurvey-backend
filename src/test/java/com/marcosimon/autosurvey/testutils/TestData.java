package com.marcosimon.autosurvey.testutils;

import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.organization.Organization;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class TestData {
  //Utils for surveys service
  public OrgSurveyDTO setupSurveyDTO;
  public final String testOrganizationId = "65129d4d661d37420021aeaf";
  public final String testOrganizationName = "TestOrganization";
  public final String testCountry = "Test Country";
  public final String updateTestCountry = "Updated Country";
  public final String testUserId = "65129c13661d37420021aeae";
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

  //Utils for survey controller

  public final OrgSurveyDTO testSurvey =
          new OrgSurveyDTO(
                  UUID.randomUUID().toString(), testCountry, 1L, 2L, 3L, 4L, 5L,
                  0L, 0L, 0L,
                  0L, "", "", "", "", "",
                  0, 0, 0, 0, 0L, "", testOrganizationId,
                  "", testUserId
          );

  public final OrgSurveyDTO testSurvey2 =
          new OrgSurveyDTO(
                  UUID.randomUUID().toString(), testCountry, 0L, 0L, 0L, 0L, 0L,
                  1L, 2L, 3L,
                  0L, "", "", "", "", "",
                  0, 0, 0, 0, 0L, "",
                  testOrganizationId,"", testUserId
          );

  public final OrgSurveyDTO updatedTestSurvey =
          new OrgSurveyDTO(
                  UUID.randomUUID().toString(), updateTestCountry, 2000L, 0L, 0L, 0L, 0L,
                  0L, 0L, 0L,
                  0L, "", "", "", "", "",
                  0, 0, 0, 0, 0L, "", testOrganizationId,
                  "", testUserId
          );

  // Utils for organizations service

  public final String testOrgName = "TestOrg0";
  public final String updateOrgName = "TestOrg";
  public final String wrongOrganizationId = "651176a534067a39d9ec6d4f";
  public final List<String> testUserIds = new ArrayList<>(Collections.singleton(testUserId));
  public final Organization testOrg = new Organization(testOrgName, testUserId);

}
