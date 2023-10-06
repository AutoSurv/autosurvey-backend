package com.marcosimon.autosurvey.currencyinfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.CountryInfoService;
import com.marcosimon.autosurvey.models.NewCurrencyInfoDTO;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currencies")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class CurrencyController {
  private final CurrencyInfoService currencyInfoService;
  private final CountryInfoService countryInfoService;

  @GetMapping
  public ResponseEntity<List<CurrencyInfo>> getAllCurrencies() {
    return ResponseEntity.ok(currencyInfoService.getAllCurrencyInfo());
  }

  @GetMapping("{id}")
  public ResponseEntity<CurrencyInfo> getCurrency(@PathVariable @NotEmpty String id) {
    return ResponseEntity.ok(currencyInfoService.getCurrencyInfoById(id));
  }

  @PostMapping
  public ResponseEntity<CurrencyInfo> createCurrency(@RequestBody NewCurrencyInfoDTO newCurrencyInfoDTO, HttpServletRequest req) {
    CountryInfo countryInfo = countryInfoService.getCountryInfoByNameAndDate(newCurrencyInfoDTO.countryName(), newCurrencyInfoDTO.date());
    CurrencyInfo currencyInfo = new CurrencyInfo(countryInfo.getCountryInfoId(), newCurrencyInfoDTO.currency(), newCurrencyInfoDTO.exchangeRate());

    URI location = URI.create((req.getRequestURI() + "/" + currencyInfo.getCurrencyInfoId()));
    return ResponseEntity.created(location).body(currencyInfo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<CurrencyInfo> updateCurrency(@PathVariable @NotEmpty String id, @RequestBody NewCurrencyInfoDTO updateCurrency) {
    CurrencyInfo currencyUpdated = currencyInfoService.updatedCurrencyInfo(id, updateCurrency);
    return ResponseEntity.accepted().body(currencyUpdated);
  }

  @DeleteMapping("{id}")
  public  void deleteCurrency(@PathVariable @NotEmpty String id) {
    currencyInfoService.deleteCurrencyInfo(id);
  }

}
