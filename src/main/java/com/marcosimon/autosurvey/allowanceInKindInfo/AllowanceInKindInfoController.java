package com.marcosimon.autosurvey.allowanceInKindInfo;

import com.marcosimon.autosurvey.models.NewAllowanceInKindDTO;
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
@RequestMapping("api/allowanceinkindinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class AllowanceInKindInfoController {

    private final AllowanceInKindInfoService allowanceInKindInfoService;

    @GetMapping
    public ResponseEntity<List<AllowanceInKindInfo>> getAllAllowanceInKindInfo() {
        return ResponseEntity.ok(allowanceInKindInfoService.getAllAllowanceInKind());
    }

    @GetMapping("{id}")
    public ResponseEntity<AllowanceInKindInfo> getAllowanceInKind(@PathVariable @NotEmpty Long id) {
        return ResponseEntity.ok(allowanceInKindInfoService.getAllowanceInKind(id));
    }

    @PostMapping
    public ResponseEntity<AllowanceInKindInfo> createAllowanceInKind(@RequestBody @NotNull NewAllowanceInKindDTO newAllowanceInKindDTO, HttpServletRequest req) {
        AllowanceInKindInfo allowanceInKindInfo = allowanceInKindInfoService.addAllowanceInKindInfo(newAllowanceInKindDTO);
        URI location = URI.create((req.getRequestURI() + "/" + allowanceInKindInfo.getAllowanceInKindInfoId()));
        return ResponseEntity.created(location).body(allowanceInKindInfo);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AllowanceInKindInfo> updateAllowanceInKind(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewAllowanceInKindDTO updateAllowanceInKindDTO) {
        AllowanceInKindInfo allowanceInKindInfo = allowanceInKindInfoService.updateAllowanceInKindInfo(id, updateAllowanceInKindDTO);
        return ResponseEntity.accepted().body(allowanceInKindInfo);
    }

    @DeleteMapping("{id}")
    public void deleteAllowanceInKindInfo(@PathVariable @NotEmpty Long id) { allowanceInKindInfoService.deleteAllowanceInKindInfo(id); }

}
