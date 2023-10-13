package com.marcosimon.autosurvey.functioninfo;

import com.marcosimon.autosurvey.models.NewFunctionInfoDTO;
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
@RequestMapping("api/functioninfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class FunctionInfoController {

    private final FunctionInfoService functionInfoService;

    @GetMapping
    public ResponseEntity<List<FunctionInfo>> getAllFunctionInfo() {
        return ResponseEntity.ok(functionInfoService.getAllFunctionInfo());
    }

    @GetMapping("{id}")
    public ResponseEntity<FunctionInfo> getFunctionInfo(@PathVariable @NotEmpty Long id) {
        return ResponseEntity.ok(functionInfoService.getFunctionInfo(id));
    }

    @PostMapping
    public ResponseEntity<FunctionInfo> createFunctionInfo(@RequestBody @NotNull NewFunctionInfoDTO newFunctionInfoDTO, HttpServletRequest req) {
        FunctionInfo functionInfo = functionInfoService.addFunctionInfo(newFunctionInfoDTO);
        URI location = URI.create((req.getRequestURI() + "/" +functionInfo.getFunctionInfoId()));
        return ResponseEntity.created(location).body(functionInfo);
    }

    @PatchMapping("{id}")
    public ResponseEntity<FunctionInfo> updateFunctionInfo(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewFunctionInfoDTO newFunctionInfoDTO) {
        FunctionInfo functionInfo = functionInfoService.updateFunctionInfo(id, newFunctionInfoDTO);
        return ResponseEntity.accepted().body(functionInfo);
    }

    @DeleteMapping("{id}")
    public void deleteFunction(@PathVariable @NotEmpty Long id) { functionInfoService.deleteFunctionInfo(id); }

}
