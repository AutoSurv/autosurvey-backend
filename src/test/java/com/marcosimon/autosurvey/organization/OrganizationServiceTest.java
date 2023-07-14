package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrganizationServiceTest {
  @Autowired
  OrganizationService organizationService;
  static String organizationName = "Test Organization 1";
  static String organizationId = "";

  @Test
  public void addNewOrganization() {
    int initialSize = organizationService.getAllOrganizations().size();

    Organization testOrganizationOne = new Organization(organizationName, null);
    OrganizationResponseDTO responseDTO = organizationService.addOrganization(testOrganizationOne);

    int newSize = organizationService.getAllOrganizations().size();
    organizationId = responseDTO.orgId();

    assertEquals(organizationName, responseDTO.orgName());
    assertTrue(newSize > initialSize);
  }

  @Test
  public void getOrganization() {
    OrganizationResponseDTO responseDTO = organizationService.getOrgById(organizationId);
    assertEquals(responseDTO.orgName(), organizationName);

  }

  @Test
  public void renameOrganization() {
    String newName = "Test Organization 2";

    OrganizationResponseDTO responseDTO = organizationService.renameOrganization(organizationId, newName);
    assertNotEquals(responseDTO.orgName(), organizationName);
    assertEquals(responseDTO.orgName(), newName);
  }

  @Test
  public void deleteOrganization() {

  }

}