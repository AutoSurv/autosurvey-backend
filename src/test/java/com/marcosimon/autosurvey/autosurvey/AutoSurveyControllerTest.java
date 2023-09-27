package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.testutils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AutoSurveyControllerTest {

  @Mock
  AutoSurveyService mockedService;
  @InjectMocks
  AutoSurveyController controller;

  @Test
  public void shouldReturnSurveyListWith2Entries() {

    Mockito.when(mockedService.getAllSurveys()).thenReturn(Arrays.asList(TestData.testSurvey, TestData.testSurvey2));

    List<OrgSurveyDTO> orgSurveyDTOList = controller.getAllSurveys().getBody();

    assertThat(orgSurveyDTOList.get(0)).isEqualTo(TestData.testSurvey);
    assertThat(orgSurveyDTOList.size()).isEqualTo(2);

  }

  @Test
  public void shouldReturnSpecificSurveyWhenRequested() {

    String id = TestData.testSurvey.id();

    Mockito.when(mockedService.getSurveyById(id)).thenReturn(TestData.testSurvey);
    OrgSurveyDTO orgSurveyDTO = controller.getSurvey(id).getBody();

    assertThat(orgSurveyDTO).isEqualTo(TestData.testSurvey);

  }

  @Test
  @NullSource
  public void shouldReturn400BadRequestWhenIdIsMissingOnGetById () {

    String id = TestData.testSurvey.id();
    Mockito.when(mockedService.getSurveyById(id)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    OrgSurveyDTO orgSurveyDTO = controller.getSurvey(null).getBody();

    assertThat(orgSurveyDTO).isEqualTo(TestData.testSurvey);
  }

  @Test
  public void shouldReturn400BadRequestWhenIdIsEmptyOnGetById () {


  }

  @Test
  public void shouldReturn400BadRequestWhenIdIsNotValidOnGetById () {


  }


  @Test
  public void shouldReturnS409ConflictedForFailedAdd() {

    HttpServletRequest mockedReq = new MockHttpServletRequest();
    Mockito.when(mockedService.addSurvey(TestData.testDTO)).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT));

    assertThrows(ResponseStatusException.class, () -> controller.addNewSurvey(TestData.testDTO, mockedReq));

  }

  @Test
  public void shouldUpdateSurvey() {

    String id = TestData.testSurvey.id();
    CreateSurveyDTO dataToUpdateDto = TestData.updatedTestDTO;
    Mockito.when(mockedService.updateSurveyData(id, dataToUpdateDto)).thenReturn(TestData.updatedTestSurvey);

    OrgSurveyDTO updatedSurveyDTO = controller.editSurvey(dataToUpdateDto, id).getBody();

    assertThat(updatedSurveyDTO).isEqualTo(TestData.updatedTestSurvey);
    assertThat(TestData.testSurvey.country()).isNotEqualTo(updatedSurveyDTO.country());

  }

  @Test
  public void shouldReturn400BadRequestWhenIdIsMissingOnUpdate () {


  }

  @Test
  public void shouldReturn400BadRequestWhenIdIsEmptyOnUpdate () {


  }

  @Test
  public void shouldReturn400BadRequestWhenIdIsNotValidOnUpdate () {


  }

  @Test
  public void shouldNotUpdateSurveyDueToEmptyBodyOnUpdate () {


  }

  @Test
  public void shouldDelete() {


  }

  @Test
  public void shouldNotDelete() {


  }

}
