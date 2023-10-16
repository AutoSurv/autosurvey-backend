package com.marcosimon.autosurvey.allowancepercentinfo;

import com.marcosimon.autosurvey.models.NewAllowancePercentageDTO;
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
@RequestMapping("api/allowancepercentinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class AllowancePercentInfoController {

    private final AllowancePercentInfoService allowancePercentInfoService;

    @GetMapping
    public ResponseEntity<List<AllowancePercentInfo>> getAllAllowancePercentInfo() {
        return ResponseEntity.ok(allowancePercentInfoService.getAllAllowancePercent());
    }

    @GetMapping("{id}")
    public ResponseEntity<AllowancePercentInfo> getAllowancePercent(@PathVariable @NotEmpty Long id) {
        return ResponseEntity.ok(allowancePercentInfoService.getAllowancePercentById(id));
    }

    @PostMapping
    public ResponseEntity<AllowancePercentInfo> createAllowancePercent(@RequestBody @NotNull NewAllowancePercentageDTO newAllowancePercentageDTO, HttpServletRequest req) {
        AllowancePercentInfo allowancePercentInfo = allowancePercentInfoService.addAllowancePercentInfo(newAllowancePercentageDTO);
        URI location = URI.create((req.getRequestURI() + "/" + allowancePercentInfo.getAllowancePercentInfoId()));
        return ResponseEntity.created(location).body(allowancePercentInfo);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AllowancePercentInfo> updateAllowancePercent(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewAllowancePercentageDTO updateAllowancePercentageDTO) {
        AllowancePercentInfo allowancePercentInfo = allowancePercentInfoService.updateAllowancePercentInfo(id, updateAllowancePercentageDTO);
        return ResponseEntity.accepted().body(allowancePercentInfo);
    }

    @DeleteMapping("{id}")
    public void deleteAllowanceInfo(@PathVariable @NotEmpty Long id) { allowancePercentInfoService.deleteAllowancePercentInfo(id); }

}
