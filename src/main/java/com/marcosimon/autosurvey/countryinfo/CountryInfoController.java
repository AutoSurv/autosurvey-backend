package com.marcosimon.autosurvey.countryinfo;

import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
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
@RequestMapping("api/countryinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class CountryInfoController {

  private final CountryInfoService countryInfoService;

  @GetMapping
  public ResponseEntity<List<CountryInfo>> getAllCountryInfo() {
    return ResponseEntity.ok(countryInfoService.getAllCountryInfo());
  }

  @GetMapping("{id}")
  public ResponseEntity<CountryInfo> getCountryInfoById(@PathVariable @NotEmpty Long id) {
    return ResponseEntity.ok(countryInfoService.getCountryInfoById(id));
  }

  @PostMapping
  public ResponseEntity<CountryInfo> createNewCountryInfo(@RequestBody @NotNull NewCountryInfoDTO newCountryInfoDTO, HttpServletRequest req) {
    CountryInfo createdCountryInfo = countryInfoService.addCountryInfo(newCountryInfoDTO);
    URI location = URI.create((req.getRequestURI() + "/" + createdCountryInfo.getCountryInfoId()));
    return ResponseEntity.created(location).body(createdCountryInfo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<CountryInfo> updateCountryInfo(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewCountryInfoDTO updateCountryInfo) {
    CountryInfo updatedCountryInfo = countryInfoService.updateCountryInfo(id, updateCountryInfo);
    return ResponseEntity.accepted().body(updatedCountryInfo);
  }

  @DeleteMapping("{id}")
  public void deleteCountry (@PathVariable @NotEmpty Long id) {
    countryInfoService.deleteCountryInfo(id);
  }

}
