package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurveyService;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.testutils.TestData;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrganizationServiceTest {
  @Autowired
  OrganizationService organizationService;
  @Autowired
  AutoSurveyService service;

  /*
  @BeforeAll
  void setUp(){
    TestData.setupOrgResponseDTO = organizationService.addOrganization(TestData.testOrg);
  }
  */

  @AfterAll
  void tearDown(){
    organizationService.deleteOrganization(TestData.testOrg.getOrgId());
    service.deleteSurvey(TestData.setupSurveyDTO.id());
  }

  @Test
  @Order(1)
  void getCorrectAllOrganizationsSize() {

    int zeroLength = 0;
    int actualResult = organizationService.getAllOrganizations().size();
    assertNotEquals(zeroLength, actualResult);

  }

  @Test
  @Order(2)
  void getWrongAllOrganizationsSize() {

    int allOrgLength = 10;
    int result = organizationService.getAllOrganizations().size();
    assertNotEquals(allOrgLength, result);

  }

  @Test
  @Order(3)
  void getCorrectOrganizationById() {

    OrganizationResponseDTO responseDTO = organizationService.getOrgById(TestData.testOrganizationId);
    assertEquals(responseDTO.orgName(), TestData.testOrganizationName);

  }

  @Test
  @Order(4)
  void getWrongOrganizationById() {
    CustomException exception =
            assertThrows(CustomException.class, () -> {
              organizationService.getOrgById(TestData.wrongOrganizationId);
            });

    String expectedMessage = "Organization is not in DB";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  @Order(5)
  void shouldAddAndDeleteOrganization() {

    OrganizationResponseDTO createdOrganizationDTO = organizationService.addOrganization(TestData.testOrg);
    assertNotNull(createdOrganizationDTO.orgId());
    assertEquals(TestData.testOrgName, createdOrganizationDTO.orgName());

    int orgQuantityBeforeDeletion = organizationService.getAllOrganizations().size();
    organizationService.deleteOrganization(createdOrganizationDTO.orgId());
    int orgQuantityAfterDeletion = organizationService.getAllOrganizations().size();

    assertNotEquals(orgQuantityAfterDeletion, orgQuantityBeforeDeletion);
  }

  @Test
  @Order(6)
  void existingOrgNameThrowExceptionOnCreatingOrganization() {

    OrganizationResponseDTO createdOrganizationDTO = organizationService.addOrganization(TestData.testOrg);

    CustomException exception =
            assertThrows(CustomException.class, () -> {
              organizationService.addOrganization(new Organization(createdOrganizationDTO.orgName(), TestData.testUserId));
            });

    String expectedMessage = "Organization is already saved";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  @Order(7)
  public void renameOrganization() {

    OrganizationResponseDTO responseDTO = organizationService.renameOrganization(TestData.testOrg.getOrgId(), TestData.updateOrgName);
    assertNotEquals(responseDTO.orgName(), TestData.testOrgName);
    assertEquals(responseDTO.orgName(), TestData.updateOrgName);

  }

  @Test
  @Order(8)
  void existingOrgNameThrowExceptionOnRenameOrganization() {

    CustomException exception =
            assertThrows(CustomException.class, () -> {
              organizationService.renameOrganization(TestData.testOrg.getOrgId(), TestData.updateOrgName);
            });

    String expectedMessage = "Organization is already saved";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  @Order(9)
  void getCorrectOrgSurveyCountry() {

    TestData.setupSurveyDTO = service.addSurvey(TestData.testDTO);
    String expectedCountry = "Test Country";
    String actualResult = organizationService.getOrgSurvey(TestData.setupSurveyDTO.id()).country();
    assertEquals(expectedCountry, actualResult);

  }

  @Test
  @Order(10)
  void getCorrectAllOrgSurveysSize() {

    int zeroLength = 0;
    int actualResult = organizationService.getOrgSurveys(TestData.testOrganizationId).size();
    assertNotEquals(zeroLength, actualResult);

  }

  @Test
  @Order(11)
  void customExceptionThrownWithWrongOrgIdWhenDeleteOrganization() {

    CustomException exception =
            assertThrows(CustomException.class, () -> {
              organizationService.deleteOrganization(TestData.wrongOrganizationId);
            });

    String expectedMessage = "Organization is not in DB";
    String actualMessage = exception.getErrorCode().getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  @Order(12)
  void shouldAddAndDeleteUser() {


  }
}