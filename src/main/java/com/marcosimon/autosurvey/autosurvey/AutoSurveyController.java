package com.marcosimon.autosurvey.autosurvey;


import com.marcosimon.autosurvey.models.CreateSurveyDTO;


import com.marcosimon.autosurvey.models.OrgSurveyDTO;
import com.marcosimon.autosurvey.organization.OrganizationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/autosurveys")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000", "https://autosurvey-frontend.vercel.app"})
public class AutoSurveyController {

  private final AutoSurveyService surveyService;
  private final OrganizationService organizationService;

  public AutoSurveyController(AutoSurveyService surveyService, OrganizationService organizationService) {
    this.surveyService = surveyService;
    this.organizationService = organizationService;
   }

  @GetMapping
  ResponseEntity<List<OrgSurveyDTO>> getAllSurveys() {
       return ResponseEntity.ok(surveyService.getAllSurveys());
  }

  @GetMapping("{id}")
  ResponseEntity<OrgSurveyDTO> getSurvey(@PathVariable String id) {

    if (id == null || id.equals("")) {
      return ResponseEntity.badRequest().build();
    }

    OrgSurveyDTO survey = surveyService.getSurveyById(id);

    if (survey == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(survey);
  }

  @PostMapping
  ResponseEntity<OrgSurveyDTO> addNewSurvey(@RequestBody CreateSurveyDTO dto, HttpServletRequest req) {
    if (dto == null) return ResponseEntity.badRequest().build();

    OrgSurveyDTO newSurvey = surveyService.addSurvey(dto);
    URI location = URI.create((req.getRequestURI() + "/" + newSurvey.id()).replace("//autosurveys","/autosurveys"));
    return ResponseEntity.created(location).body(newSurvey);
  }

  @PatchMapping("{id}")
  ResponseEntity<OrgSurveyDTO> editSurvey(@RequestBody CreateSurveyDTO dto, @PathVariable String id) {
    System.out.println("dto: " + dto);
    if (id == null || id.equals("")) return ResponseEntity.badRequest().build();

    OrgSurveyDTO updatedSurvey = surveyService.updateSurveyData(id, dto);
    if (updatedSurvey == null) return ResponseEntity.notFound().build();

    return ResponseEntity.accepted().body(updatedSurvey);
  }

  @DeleteMapping("{id}")
  ResponseEntity deleteSurvey(@PathVariable String id) {
    if (id == null || id.equals("")) return ResponseEntity.badRequest().build();
    surveyService.deleteSurvey(id);
    return ResponseEntity.noContent().build();
  }

}