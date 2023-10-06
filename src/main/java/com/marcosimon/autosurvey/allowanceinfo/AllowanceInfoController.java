package com.marcosimon.autosurvey.allowanceinfo;

import com.marcosimon.autosurvey.models.NewAllowanceValueDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/allowanceinfo")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class AllowanceInfoController {

  private final AllowanceInfoService allowanceInfoService;


  @GetMapping
  public ResponseEntity<List<AllowanceInfo>> getAllAllowances() {
    return ResponseEntity.ok(allowanceInfoService.getAllAllowances());
  }

  @GetMapping("{id}")
  public ResponseEntity<AllowanceInfo> getAllowanceInfo(@PathVariable @NotEmpty String id) {
    return ResponseEntity.ok(allowanceInfoService.getAllowanceById(id));
  }

  @PostMapping
  public ResponseEntity<AllowanceInfo> createAllowanceInfo(@RequestBody NewAllowanceValueDTO newAllowanceValueDTO, @RequestBody NewMsfOrgInfoDTO newMsfOrgInfoDTO, HttpServletRequest req) {
    AllowanceInfo allowanceInfo = allowanceInfoService.addAllowance(newAllowanceValueDTO, newMsfOrgInfoDTO);

    URI location = URI.create((req.getRequestURI() + "/" + allowanceInfo.getAllowanceInfoId()));
    return ResponseEntity.created(location).body(allowanceInfo);

  }

  @PatchMapping("{id}")
  public ResponseEntity<AllowanceInfo> updateAllowanceInfo(@PathVariable @NotEmpty String id, @RequestBody NewAllowanceValueDTO newAllowanceValueDTO) {
    AllowanceInfo allowanceInfoUpdated = allowanceInfoService.updateAllowance(id, newAllowanceValueDTO);
    return ResponseEntity.accepted().body(allowanceInfoUpdated);
  }

  @DeleteMapping("{id}")
  public void deleteAllowance(@PathVariable @NotEmpty String id) {
    allowanceInfoService.deleteById(id);
  }


}
