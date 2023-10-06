package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orginfo")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class MsfOrgInfoController {

  private final MsfOrgInfoService msfOrgInfoService;

  @GetMapping
  public ResponseEntity<List<MsfOrgInfo>> getAllMsfOrgInfo() {
    return ResponseEntity.ok(msfOrgInfoService.getAllMsfOrgInfo());
  }

  @GetMapping("{id}")
  public ResponseEntity<MsfOrgInfo> getMsfOrgInfo(@PathVariable @NotEmpty String id) {
    return ResponseEntity.ok(msfOrgInfoService.getMsfOrgInfoById(id));
  }

  @PostMapping
  public ResponseEntity<MsfOrgInfo> createMsfOrgInfo(@RequestBody NewCountryInfoDTO newCountryInfoDTO, @RequestBody NewMsfOrgInfoDTO newMsfOrgInfoDTO, HttpServletRequest req) {
    MsfOrgInfo msfOrgInfo = msfOrgInfoService.addMsfOrgInfo(newCountryInfoDTO, newMsfOrgInfoDTO);

    URI location = URI.create((req.getRequestURI() + "/" + msfOrgInfo.getOrgId()));
    return ResponseEntity.created(location).body(msfOrgInfo);

  }

  @PatchMapping("{id}")
  public ResponseEntity<MsfOrgInfo> updateMsfOrgInfo(@PathVariable @NotEmpty String id, @RequestBody NewMsfOrgInfoDTO newMsfOrgInfoDTO) {
    return ResponseEntity.ok(msfOrgInfoService.updateMsfOrgInfo(id, newMsfOrgInfoDTO));
  }

  @DeleteMapping("{id}")
  public void deleteMsfOrgInfo(@PathVariable @NotEmpty String id) {
    msfOrgInfoService.deleteMsfOrgInfo(id);
  }

}
