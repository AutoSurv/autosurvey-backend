package com.marcosimon.autosurvey.allowancepercentinfo;

import com.marcosimon.autosurvey.models.NewAllowancePercentageDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/allowancepercentinfo")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class AllowancePercentInfoController {

  private final AllowancePercentInfoService allowancePercentInfoService;

  @GetMapping
  public ResponseEntity<List<AllowancePercentInfo>> getAllAllowances() {
    return ResponseEntity.ok(allowancePercentInfoService.getAllAllowances());
  }

  @GetMapping("{id}")
  public ResponseEntity<AllowancePercentInfo> getAllowanceInfo(@PathVariable @NotEmpty String id) {
    return ResponseEntity.ok(allowancePercentInfoService.getAllowanceById(id));
  }

  @PostMapping
  public ResponseEntity<AllowancePercentInfo> createAllowanceInfo(@RequestBody NewAllowancePercentageDTO newAllowancePercentageDTO, @RequestBody NewMsfOrgInfoDTO newMsfOrgInfoDTO, HttpServletRequest req) {
    AllowancePercentInfo allowanceInfo = allowancePercentInfoService.addAllowance(newAllowancePercentageDTO, newMsfOrgInfoDTO);

    URI location = URI.create((req.getRequestURI() + "/" + allowanceInfo.getAllowancePercentInfoId()));
    return ResponseEntity.created(location).body(allowanceInfo);

  }

  @PatchMapping("{id}")
  public ResponseEntity<AllowancePercentInfo> updateAllowanceInfo(@PathVariable @NotEmpty String id, @RequestBody NewAllowancePercentageDTO newAllowancePercentageDTO) {
    AllowancePercentInfo allowanceInfoUpdated = allowancePercentInfoService.updateAllowance(id, newAllowancePercentageDTO);
    return ResponseEntity.accepted().body(allowanceInfoUpdated);
  }

  @DeleteMapping("{id}")
  public void deleteAllowance(@PathVariable @NotEmpty String id) {
    allowancePercentInfoService.deleteById(id);
  }
}
