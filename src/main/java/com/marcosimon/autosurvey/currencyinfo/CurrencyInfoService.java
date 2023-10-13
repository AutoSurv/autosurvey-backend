package com.marcosimon.autosurvey.currencyinfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewCurrencyInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CurrencyInfoService {

    private final ICurrencyInfoDbRepository currencyInfoDbRepository;
    private final ICountryInfoDbRepository countryInfoDbRepository;

    public List<CurrencyInfo> getAllCurrencyInfo() { return currencyInfoDbRepository.findAll(); }

    public CurrencyInfo getCurrencyInfoById(Long id) {
        return currencyInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized CurrencyInfo addCurrencyInfo(NewCurrencyInfoDTO newCurrencyInfoDTO) {
        CountryInfo countryInfo = countryInfoDbRepository
                .findByNameAndDate(newCurrencyInfoDTO.countryName(), newCurrencyInfoDTO.date())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

        return currencyInfoDbRepository.save(new CurrencyInfo(newCurrencyInfoDTO.currency(), newCurrencyInfoDTO.exchangeRate(), countryInfo));
    }

    @Transactional
    public synchronized CurrencyInfo updatedCurrencyInfo(Long id, NewCurrencyInfoDTO updateCurrencyInfoDTO) {
        CurrencyInfo storedCurrencyInfo = currencyInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));

        if (updateCurrencyInfoDTO.currency() != null && !updateCurrencyInfoDTO.currency().isEmpty()) {
            storedCurrencyInfo.setCurrency(updateCurrencyInfoDTO.currency());
        }

        if (updateCurrencyInfoDTO.exchangeRate() != null && updateCurrencyInfoDTO.exchangeRate() >= 0) {
            storedCurrencyInfo.setExchangeRate(updateCurrencyInfoDTO.exchangeRate());
        }

        return currencyInfoDbRepository.save(storedCurrencyInfo);
    }

    public void deleteCurrencyInfo(Long id) {
        currencyInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));
        currencyInfoDbRepository.deleteById(id);
    }

}
