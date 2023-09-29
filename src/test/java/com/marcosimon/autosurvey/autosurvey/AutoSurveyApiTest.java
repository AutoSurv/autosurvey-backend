package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.OrgSurveyDTO;

import com.marcosimon.autosurvey.testutils.TestData;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@CrossOrigin(origins = "*")
//@ActiveProfiles("test")
public class AutoSurveyApiTest {

  @Value("${server.port}")
  private int configPort;
  @Autowired
  AutoSurveyService service;
  @Autowired
  private TestRestTemplate restTemplate;

  @AfterEach
  public void cleanUp() {
    if (TestData.setupSurveyDTO != null)
    service.deleteSurvey(TestData.testSurvey.id());
  }

  @Test
  public void portShouldBeTestPort(){

    assertThat(configPort).isEqualTo(8080);
  }

  @Test
  public void assertThatTheNewSurveyIsCorrectlyReturned() {
    //Arrange
    OrgSurveyDTO testOrgSurvey = TestData.testSurvey;

    //Act
    ResponseEntity<OrgSurveyDTO> exchange1 = restTemplate
            .exchange("http://localhost:" + configPort + "/api/autosurveys", HttpMethod.POST, new HttpEntity<OrgSurveyDTO>(testOrgSurvey), OrgSurveyDTO.class);
    //Assert
    OrgSurveyDTO body = exchange1.getBody();
    assertThat(body.country()).isEqualTo("Test Country");
    assertThat(exchange1.getHeaders().getFirst("location")).isEqualTo("/api/autosurveys/" + body.id());
    service.deleteSurvey(body.id());
  }
  /*
  @Test
  public void getSurveyById(@Autowired MongoTemplate mongoTemplate) throws URISyntaxException {

    //1. create a fake document
    //2. save document on db via controller
    //addOrganization(@RequestBody CreateOrganizationDTO dto, HttpServletRequest req)
    //3. check controller response: status, header, etc..

    //ResponseEntity<SaltCourse> exchange = restTemplate.exchange(url,
    //HttpMethod.POST,
    //new HttpEntity<SaltCourse>(theNewCourse),
    //SaltCourse.class);

//    OrganizationResponseDTO addOrgResponse = WebClient.create()
//            .post()
//            .uri(new URI("localhost:8080//api/organiztions"))
//            .body(TestData.fakeOrgDTO, OrganizationResponseDTO.class)
//            .retrieve()
//            .bodyToMono(OrganizationResponseDTO.class)
//            .blockOptional()
//            .orElseThrow();

    String id = TestData.testSurvey.id();
    String url = "http://localhost:8080/api/autosurveys/" + id;
// act
    ResponseEntity<OrgSurveyDTO> exchange = restTemplate.exchange(url,
            HttpMethod.POST,
            new HttpEntity<OrgSurveyDTO>(TestData.testSurvey),
            OrgSurveyDTO.class);
// assert
    assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(exchange.getHeaders().getFirst("location")).isEqualTo("/api/autosurveys/" + id);
  }
   */
  @Test
  public void assertThatACreatedSurveyCanBeRetrieved() {
    // arrange
    // act
    // -- make a call to the API to create the joke
    ResponseEntity<OrgSurveyDTO> createdSurvey = restTemplate.exchange("http://localhost:" + configPort + "/api/autosurveys", HttpMethod.POST, new HttpEntity<OrgSurveyDTO>(TestData.testSurvey), OrgSurveyDTO.class);
    ResponseEntity<OrgSurveyDTO> retrieveSurvey = restTemplate.getForEntity("http://localhost:" + configPort + "/api/autosurveys/" + createdSurvey.getBody().id(), OrgSurveyDTO.class);

    // assert

    assertThat(createdSurvey.getBody().country()).isEqualTo("Test Country");
    assertThat(createdSurvey.getHeaders().getFirst("location")).isEqualTo("/api/autosurveys/"+createdSurvey.getBody().id());
    assertThat(retrieveSurvey.getBody().country()).isEqualTo("Test Country");
    service.deleteSurvey(createdSurvey.getBody().id());
  }


}
