package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.models.OrganizationResponseDTO;
import com.marcosimon.autosurvey.organization.OrganizationController;
import com.marcosimon.autosurvey.testutils.TestData;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.9.2"})
public class AutoSurveyApiTest {

  @Autowired
  private TestRestTemplate restTemplate;


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


}
