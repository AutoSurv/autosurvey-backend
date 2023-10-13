package com.marcosimon.autosurvey.currencyinfo;

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
@RequestMapping("api/currencyinfos")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class CurrencyInfoController {

  private final CurrencyInfoService currencyInfoService;

  @GetMapping
  public ResponseEntity<List<CurrencyInfo>> getAllCurrencies() {
    return ResponseEntity.ok(currencyInfoService.getAllCurrencyInfo());
  }

  @GetMapping("{id}")
  public ResponseEntity<CurrencyInfo> getCurrency(@PathVariable @NotEmpty Long id) {
    return ResponseEntity.ok(currencyInfoService.getCurrencyInfoById(id));
  }

  @PostMapping
  public ResponseEntity<CurrencyInfo> createCurrency(@RequestBody @NotNull NewCurrencyInfoDTO newCurrencyInfoDTO, HttpServletRequest req) {
    CurrencyInfo currencyInfo = currencyInfoService.addCurrencyInfo(newCurrencyInfoDTO);
    URI location = URI.create((req.getRequestURI() + "/" + currencyInfo.getCurrencyInfoId()));
    return ResponseEntity.created(location).body(currencyInfo);
  }

  @PatchMapping("{id}")
  public ResponseEntity<CurrencyInfo> updateCurrency(@PathVariable @NotEmpty Long id, @RequestBody @NotNull NewCurrencyInfoDTO updateCurrency) {
    CurrencyInfo currencyUpdated = currencyInfoService.updatedCurrencyInfo(id, updateCurrency);
    return ResponseEntity.accepted().body(currencyUpdated);
  }

  @DeleteMapping("{id}")
  public  void deleteCurrency(@PathVariable @NotEmpty Long id) {
    currencyInfoService.deleteCurrencyInfo(id);
  }

}
