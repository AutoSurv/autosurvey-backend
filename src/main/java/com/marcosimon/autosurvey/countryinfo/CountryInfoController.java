package com.marcosimon.autosurvey.countryinfo;

import com.marcosimon.autosurvey.currencyinfo.CurrencyInfoService;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.models.NewCurrencyInfoDTO;
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
  private final CurrencyInfoService currencyService;

  @GetMapping
  public ResponseEntity<List<CountryInfo>> getAllCountryInfo() {
    return ResponseEntity.ok(countryInfoService.getAllCountryInfo());
  }

  @GetMapping("{id}")
  public ResponseEntity<CountryInfo> getCountryInfoById(@PathVariable @NotEmpty String id) {
    return ResponseEntity.ok(countryInfoService.getCountryInfoById(id));
  }

  @PostMapping
  public ResponseEntity<CountryInfo> createNewCountryInfo(@RequestBody NewCountryInfoDTO newCountryInfoDTO, HttpServletRequest req) {
    CountryInfo createdCountryInfo = countryInfoService.addCountryInfo(newCountryInfoDTO);

    URI location = URI.create((req.getRequestURI() + "/" + createdCountryInfo.getCountryInfoId()));
    return ResponseEntity.created(location).body(createdCountryInfo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<CountryInfo> updateCountryInfo(@PathVariable @NotEmpty  String id, @RequestBody @NotNull NewCountryInfoDTO updateCountryInfo) {
    CountryInfo updatedCountryInfo = countryInfoService.updateCountryInfo(id, updateCountryInfo);

    return ResponseEntity.accepted().body(updatedCountryInfo);
  }

  @DeleteMapping("{id}")
  public void deleteCountry (@PathVariable @NotEmpty String id) {
    countryInfoService.deleteCountryInfo(id);
  }

}
