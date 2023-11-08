package com.marcosimon.autosurvey.benchmarkinfo;

import com.marcosimon.autosurvey.models.NewBenchmarkInfoDTO;
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
@RequestMapping("api/benchmarkinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class BenchmarkInfoController {

  private final BenchmarkInfoService benchmarkInfoService;

  @GetMapping
  public ResponseEntity<List<BenchmarkInfo>> getAllBenchmarkInfo() {
    return ResponseEntity.ok(benchmarkInfoService.getAllBenchmarkInfo());
  }

  @GetMapping("{id}")
  public ResponseEntity<BenchmarkInfo> getBenchmarkInfoById(@PathVariable @NotEmpty String id) {
    return ResponseEntity.ok(benchmarkInfoService.getBenchmarkInfoById(id));
  }

  @PostMapping
  public ResponseEntity<BenchmarkInfo> createNewBenchmarkInfo(@RequestBody @NotNull NewBenchmarkInfoDTO newBenchmarkInfoDTO, HttpServletRequest req) {
    BenchmarkInfo createdBenchmarkInfo = benchmarkInfoService.addBenchmarkInfo(newBenchmarkInfoDTO);
    URI location = URI.create((req.getRequestURI() + "/" + createdBenchmarkInfo.getBenchmarkInfoId()));
    return ResponseEntity.created(location).body(createdBenchmarkInfo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<BenchmarkInfo> updateBenchmarkInfo(@PathVariable @NotEmpty String id, @RequestBody @NotNull NewBenchmarkInfoDTO updateBenchmarkInfo) {
    BenchmarkInfo updatedBenchmarkInfo = benchmarkInfoService.updateBenchmarkInfo(id, updateBenchmarkInfo);
    return ResponseEntity.accepted().body(updatedBenchmarkInfo);
  }

  @DeleteMapping("{id}")
  public void deleteBenchmarkInfo (@PathVariable @NotEmpty String id) {
    benchmarkInfoService.deleteBenchmarkInfo(id);
  }

}
