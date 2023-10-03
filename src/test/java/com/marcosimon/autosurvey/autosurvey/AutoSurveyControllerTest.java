package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.survey.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.survey.OrgSurveyDTO;
import com.marcosimon.autosurvey.testutils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
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
    public void shouldReturn400BadRequestWhenIdIsMissingOnGetById () {

        Mockito.when(mockedService.getSurveyById(null)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.getSurvey(null));
    }

    @Test
    public void shouldReturn400BadRequestWhenIdIsEmptyOnGetById () {

        Mockito.when(mockedService.getSurveyById("")).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.getSurvey(""));

    }

    @Test
    public void shouldReturn400BadRequestWhenIdIsNotValidOnGetById () {

        Mockito.when(mockedService.getSurveyById("1")).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.getSurvey("1"));

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

        Mockito.when(mockedService.updateSurveyData(null,TestData.updatedTestDTO)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.editSurvey(TestData.updatedTestDTO,null));

    }

    @Test
    public void shouldReturn400BadRequestWhenIdIsEmptyOnUpdate () {

        Mockito.when(mockedService.updateSurveyData("",TestData.updatedTestDTO)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.editSurvey(TestData.updatedTestDTO,""));

    }


    @Test
    public void shouldReturn400BadRequestWhenIdIsNotValidOnUpdate () {

        Mockito.when(mockedService.updateSurveyData("1", TestData.updatedTestDTO)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.editSurvey(TestData.updatedTestDTO,"1"));

    }

    @Test
    public void shouldNotUpdateSurveyDueToEmptyBodyOnUpdate () {

        Mockito.when(mockedService.updateSurveyData(TestData.surveyId, null)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.editSurvey(null, TestData.surveyId));

    }

    @Test
    public void shouldDelete() {

        Mockito.when(mockedService.deleteSurvey(TestData.surveyId)).thenReturn("Survey deleted!");

        String deleteMSG = controller.deleteSurvey(TestData.surveyId).getBody();
        assertThat((deleteMSG)).isEqualTo("Survey deleted!");

    }

    @Test
    public void shouldReturn400BadRequestWhenIdIsMissingOnDelete () {

        Mockito.when(mockedService.deleteSurvey(null)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.deleteSurvey(null));

    }

    @Test
    public void shouldReturn400BadRequestWhenIdIsEmptyOnDelete () {

        Mockito.when(mockedService.deleteSurvey("")).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.deleteSurvey(""));

    }


    @Test
    public void shouldReturn400BadRequestWhenIdIsNotValidOnDelete () {

        Mockito.when(mockedService.deleteSurvey("1")).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> controller.deleteSurvey("1"));
    }

}
