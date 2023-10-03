package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping
  public ResponseEntity<MsfOrgInfo> createMsfOrgInfo(@RequestBody NewMsfOrgInfoDTO newMsfOrgInfoDTO) {
    return ResponseEntity.ok(msfOrgInfoService.createMsfOrgInfo(newMsfOrgInfoDTO));
  }

/*  @PatchMapping
  public ResponseEntity<MsfOrgInfo> updateMsfOrgInfo() {
    return ResponseEntity.ok(msfOrgInfoService.updateMsfOrgInfo());
  }*/

/*  @DeleteMapping
  public ResponseEntity<MsfOrgInfo> deleteMsfOrgInfo() {
    return ResponseEntity.ok(msfOrgInfoService.deleteMsfOrgInfo());
  }*/

}
