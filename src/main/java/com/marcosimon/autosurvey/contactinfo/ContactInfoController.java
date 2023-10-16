package com.marcosimon.autosurvey.contactinfo;

import com.marcosimon.autosurvey.models.NewContactInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/contactinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class ContactInfoController {

  private final ContactInfoService contactInfoService;

  @GetMapping
  public ResponseEntity<List<ContactInfo>> getAllContacts() {
    return ResponseEntity.ok(contactInfoService.getAllContacts());
  }

  @GetMapping("{id}")
  public ResponseEntity<ContactInfo> getContact(@PathVariable @NotEmpty Long id) {
    return ResponseEntity.ok(contactInfoService.getContactById(id));
  }

  @PostMapping
  public ResponseEntity<ContactInfo> createContact(@RequestBody @NotNull NewContactInfoDTO newContactInfoDTO, HttpServletRequest req) {
    ContactInfo contactInfo = contactInfoService.addContactInfo(newContactInfoDTO);
    URI location = URI.create((req.getRequestURI() + "/" + contactInfo.getContactInfoId()));
    return ResponseEntity.created(location).body(contactInfo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<ContactInfo> updateContact(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewContactInfoDTO newContactInfoDTO) {
    ContactInfo contactInfoUpdated = contactInfoService.updateContactInfo(id, newContactInfoDTO);
    return ResponseEntity.accepted().body(contactInfoUpdated);
  }

  @DeleteMapping("{id}")
  public void deleteContact(@PathVariable @NotEmpty Long id) { contactInfoService.deleteContactInfo(id); }

}
