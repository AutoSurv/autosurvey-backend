package com.marcosimon.autosurvey.generalAllowances;

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
    public ResponseEntity<List<GeneralAllowances>> getAllAllowanceInfo() {
        return ResponseEntity.ok(allowanceInfoService.getAllAllowance());
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralAllowances> getAllowance(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(allowanceInfoService.getAllowanceById(id));
    }

    /*@PostMapping
    public ResponseEntity<GeneralAllowances> createAllowance(@RequestBody @NotNull NewAllowanceValueDTO newAllowanceValueDTO, HttpServletRequest req) {
        GeneralAllowances generalAllowances = allowanceInfoService.addAllowanceInfo(newAllowanceValueDTO);
        URI location = URI.create((req.getRequestURI() + "/" + generalAllowances.getAllowanceInfoId()));
        return ResponseEntity.created(location).body(generalAllowances);
    }*/

    @PatchMapping("{id}")
    public ResponseEntity<GeneralAllowances> updateAllowance(@PathVariable @NotEmpty String id, @RequestBody @NotNull NewAllowanceValueDTO newAllowanceValueDTO) {
        GeneralAllowances generalAllowances = allowanceInfoService.updateAllowanceInfo(id, newAllowanceValueDTO);
        return ResponseEntity.accepted().body(generalAllowances);
    }

    @DeleteMapping("{id}")
    public void deleteAllowance(@PathVariable @NotEmpty String id) { allowanceInfoService.deleteAllowanceInfo(id); }

}
