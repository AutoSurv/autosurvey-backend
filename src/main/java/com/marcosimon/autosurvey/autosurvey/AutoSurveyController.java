package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.countrygroup.CountryGroupService;
import com.marcosimon.autosurvey.models.AutoSurveyResponseDTO;
import com.marcosimon.autosurvey.models.CreateSurveyDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/autosurveys")
@CrossOrigin(origins = "http://localhost:3000")
public class AutoSurveyController {

  Logger logger = Logger.getLogger(AutoSurveyRepository.class.getName());

  private final AutoSurveyService surveyService;
  private final CountryGroupService countryGroupService;

  public AutoSurveyController(AutoSurveyService surveyService, CountryGroupService countryGroupService) {
    this.surveyService = surveyService;
    this.countryGroupService = countryGroupService;
  }

  @GetMapping
  ResponseEntity<List<AutoSurveyResponseDTO>> getAllSurveys() {
    List<AutoSurvey> body = surveyService.getAllSurveys();
    logger.info(body.get(0).getLocationGiven());
    return ResponseEntity.ok(body.stream().map(SurveyConverter::toResponseDto).collect(Collectors.toList()));
  }

  @GetMapping("{id}")
  ResponseEntity<AutoSurveyResponseDTO> getSurvey(@PathVariable String id) {

    if (id == null || id.equals("")) {
      return ResponseEntity.badRequest().build();
    }

    //if id is a proper UUID
    AutoSurvey survey = surveyService.getSurveyById(id);

    if (survey == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(SurveyConverter.toResponseDto(survey));
  }

  @PostMapping
  ResponseEntity<AutoSurveyResponseDTO> addNewSurvey(@RequestBody CreateSurveyDTO newSurvey, HttpServletRequest req) {
    AutoSurvey survey = SurveyConverter.fromDto(newSurvey);
    AutoSurvey autoSurvey = surveyService.saveSurvey(survey);
    URI location = URI.create((req.getRequestURI() + "/" + autoSurvey.getId()));
    return ResponseEntity.created(location).body(SurveyConverter.toResponseDto(autoSurvey));
  }


  @PatchMapping("{id}")
  ResponseEntity<AutoSurveyResponseDTO> editSurvey(@RequestBody CreateSurveyDTO survey, @PathVariable String id, HttpServletRequest req) {
    if (id.equals("")) return ResponseEntity.badRequest().build();
    AutoSurvey surv = SurveyConverter.fromDto(survey);
    surv.setId(id);
    AutoSurvey updatedSurvey = surveyService.updateSurveyData(surv);
    if (updatedSurvey == null) return ResponseEntity.notFound().build();

    return ResponseEntity.accepted().body(SurveyConverter.toResponseDto(updatedSurvey));
  }

  @DeleteMapping("{id}")
  ResponseEntity deleteSurvey(@PathVariable String id) {
    if (id.equals("")) return ResponseEntity.badRequest().build();
    surveyService.deleteSurvey(id);
    return ResponseEntity.noContent().build();
  }

}