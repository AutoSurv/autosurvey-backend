package com.marcosimon.autosurvey.contactinfo;

import com.marcosimon.autosurvey.models.NewContactInfoDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/contactinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class ContactInfoController {

  private final ContactInfoService contactInfoService;

  @GetMapping
  public ResponseEntity<List<ContactInfo>> getAllContacts() {
    return null;
  }

  @GetMapping("{id}")
  public ResponseEntity<ContactInfo> getContact(@PathVariable @NotEmpty String id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<ContactInfo> createContact(@RequestBody NewContactInfoDTO newContactInfoDTO, @RequestBody NewMsfOrgInfoDTO newMsfOrgInfoDTO, HttpServletRequest req) {
    return null;
  }

  @PatchMapping("{id}")
  public ResponseEntity<ContactInfo> updateContact(@PathVariable @NotEmpty String id, @RequestBody NewContactInfoDTO newContactInfoDTO) {
    return null;
  }

  @DeleteMapping("{id}")
  public void deleteContact(@PathVariable @NotEmpty String id) {

  }

}
