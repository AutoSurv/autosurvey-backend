package com.marcosimon.autosurvey.autosurvey;


import com.marcosimon.autosurvey.models.AutoSurveyDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autosurveys")
@CrossOrigin(origins = "http://localhost:3000")
public class AutoSurveyController {
  @Autowired
  AutoSurveyService autosurveyService;

  @GetMapping
  ResponseEntity<List<AutoSurveyDTO>> getAllSurveys() {
    List<AutoSurveyDTO> surveyDTOList = autosurveyService.getAllSurvey();
    if (surveyDTOList == null) {
      return ResponseEntity.notFound().build(); //list doesn't exist
    }

    if (surveyDTOList.size() == 0) {
      return ResponseEntity.notFound().build(); //list is empty
    }

    return ResponseEntity.ok().body(surveyDTOList);
  }

  @GetMapping("{id}")
  ResponseEntity<AutoSurveyDTO> getSurvey(@PathVariable String id) {

    if (id == null || id.equals("")) {
      return ResponseEntity.badRequest().build();
    }

    //if id is a proper UUID
    AutoSurveyDTO surveyDTO = autosurveyService.getSurveyById(id);

    if (surveyDTO == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(surveyDTO);
  }

  @PostMapping("{id}")
  ResponseEntity<AutoSurveyDTO> addNewSurvey(@PathVariable String id, HttpServletRequest req) {
    return null;
  }

  @PatchMapping("{id}")
  ResponseEntity<AutoSurveyDTO> editSurvey(@PathVariable String id, @RequestBody AutoSurveyDTO autoSurveyDTO, HttpServletRequest req) {
    return null;
  }

  @DeleteMapping("{id}")
  ResponseEntity deleteSurvey(@PathVariable String id) {
    return null;
  }


}
