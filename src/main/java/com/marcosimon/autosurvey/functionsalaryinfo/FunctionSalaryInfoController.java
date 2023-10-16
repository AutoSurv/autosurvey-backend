package com.marcosimon.autosurvey.functionsalaryinfo;

import com.marcosimon.autosurvey.models.NewFunctionSalaryInfoDTO;
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
@RequestMapping("api/functionsalaryinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class FunctionSalaryInfoController {

    private final FunctionSalaryInfoService salaryInfoService;

    @GetMapping
    public ResponseEntity<List<FunctionSalaryInfo>> getAllFunctionSalaryInfo() {
        return ResponseEntity.ok(salaryInfoService.getAllFunctionSalaryInfo());
    }

    @GetMapping("{id}")
    public ResponseEntity<FunctionSalaryInfo> getFunctionSalaryInfo(@PathVariable @NotEmpty Long id) {
        return ResponseEntity.ok(salaryInfoService.getFunctionSalaryInfo(id));
    }

    @PostMapping
    public ResponseEntity<FunctionSalaryInfo> createFunctionSalaryInfo(@RequestBody @NotNull NewFunctionSalaryInfoDTO newFunctionSalaryInfoDTO, HttpServletRequest req) {
        FunctionSalaryInfo functionSalaryInfo = salaryInfoService.addFunctionSalaryInfo(newFunctionSalaryInfoDTO);
        URI location = URI.create((req.getRequestURI() + "/" + functionSalaryInfo.getFunctionSalaryInfoId()));
        return ResponseEntity.created(location).body(functionSalaryInfo);
    }

    @PatchMapping("{id}")
    public ResponseEntity<FunctionSalaryInfo> updateFunctionSalaryInfo(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewFunctionSalaryInfoDTO updateFunctionSalaryInfoDTO) {
        FunctionSalaryInfo functionSalaryInfo = salaryInfoService.updateFunctionSalaryInfo(id, updateFunctionSalaryInfoDTO);
        return ResponseEntity.accepted().body(functionSalaryInfo);
    }

    @DeleteMapping("{id}")
    public void deleteFunctionSalaryInfo(@PathVariable @NotEmpty Long id) { salaryInfoService.deleteFunctionSalaryInfo(id); }

}
