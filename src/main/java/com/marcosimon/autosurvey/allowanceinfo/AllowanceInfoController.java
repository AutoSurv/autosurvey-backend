package com.marcosimon.autosurvey.allowanceinfo;

import com.marcosimon.autosurvey.models.NewAllowanceValueDTO;
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
@RequestMapping("api/allowanceinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class AllowanceInfoController {

    private final AllowanceInfoService allowanceInfoService;

    @GetMapping
    public ResponseEntity<List<AllowanceInfo>> getAllAllowanceInfo() {
        return ResponseEntity.ok(allowanceInfoService.getAllAllowance());
    }

    @GetMapping("{id}")
    public ResponseEntity<AllowanceInfo> getAllowance(@PathVariable @NotEmpty Long id) {
        return ResponseEntity.ok(allowanceInfoService.getAllowanceById(id));
    }

    @PostMapping
    public ResponseEntity<AllowanceInfo> createAllowance(@RequestBody @NotNull NewAllowanceValueDTO newAllowanceValueDTO, HttpServletRequest req) {
        AllowanceInfo allowanceInfo = allowanceInfoService.addAllowanceInfo(newAllowanceValueDTO);
        URI location = URI.create((req.getRequestURI() + "/" + allowanceInfo.getAllowanceInfoId()));
        return ResponseEntity.created(location).body(allowanceInfo);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AllowanceInfo> updateAllowance(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewAllowanceValueDTO newAllowanceValueDTO) {
        AllowanceInfo allowanceInfo = allowanceInfoService.updateAllowanceInfo(id, newAllowanceValueDTO);
        return ResponseEntity.accepted().body(allowanceInfo);
    }

    @DeleteMapping("{id}")
    public void deleteAllowance(@PathVariable @NotEmpty Long id) { allowanceInfoService.deleteAllowanceInfo(id); }

}
