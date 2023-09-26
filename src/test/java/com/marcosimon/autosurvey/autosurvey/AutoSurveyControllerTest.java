package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.testutils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AutoSurveyControllerTest {

    private final OrgSurveyDTO testOrgSurvey = new OrgSurveyDTO(UUID.randomUUID().toString(), "Norway", 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L,
            0L, "", "", "", "", "",
            0, 0, 0, 0, 0L, "", TestData.testOrganizationId,
            "", TestData.testUserId);

    private final CreateSurveyDTO testSurveyDTO = TestData.testDTO;

    @Mock
    AutoSurveyService mockedService;

    @InjectMocks
    AutoSurveyController controller;

    @Test
    public void shouldReturnOrgSurveyListWith2Entries() {
        OrgSurveyDTO testOrgSurvey2 = new OrgSurveyDTO(UUID.randomUUID().toString(), "Sweden", 0L, 0L, 0L, 0L, 0L,
                0L, 0L, 0L,
                0L, "", "", "", "", "",
                0, 0, 0, 0, 0L, "", TestData.testOrganizationId,
                "", TestData.testUserId);
        Mockito.when(mockedService.getAllSurveys()).thenReturn(Arrays.asList(testOrgSurvey, testOrgSurvey2));
        List<OrgSurveyDTO> orgSurveyDTOList = controller.getAllSurveys().getBody();

        assertThat(orgSurveyDTOList.get(0)).isEqualTo(testOrgSurvey);
        assertThat(orgSurveyDTOList.size()).isEqualTo(2);

    }

    @Test
    public void shouldReturnSpecificSurveyWhenRequested() {
        String id = testOrgSurvey.id();
        OrgSurveyDTO expectedOrgSurvey = new OrgSurveyDTO(id, "Sweden", 0L, 0L, 0L, 0L, 0L,
                0L, 0L, 0L,
                0L, "", "", "", "", "",
                0, 0, 0, 0, 0L, "", TestData.testOrganizationId,
                "", TestData.testUserId);
        Mockito.when(mockedService.getSurveyById(id)).thenReturn(testOrgSurvey);

        OrgSurveyDTO orgSurvey = controller.getSurvey(id).getBody();

        assertThat(orgSurvey).isEqualTo(testOrgSurvey);
    }

    @Test
    public void shouldReturnStatus409ConflictedForFailedAdd() {


        HttpServletRequest mockedReq = new MockHttpServletRequest();
        Mockito.when(mockedService.addSurvey(TestData.testDTO)).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT));

        assertThrows( ResponseStatusException.class, ()-> controller.addNewSurvey(TestData.testDTO, mockedReq));

    }

}