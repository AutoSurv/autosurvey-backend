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

    public CurrencyInfo getCurrencyInfoById(String id) {
        return currencyInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized CurrencyInfo addCurrencyInfo(String countryName, String date, NewCurrencyInfoDTO newCurrencyInfoDTO) {
        CountryInfo countryInfo = countryInfoDbRepository
                .findByNameAndDate(countryName, date)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

        currencyInfoDbRepository
                .findById(countryInfo.getCountryInfoId())
                        .ifPresent( info -> {
                            throw new CustomException(ALREADY_SAVED_CURRENCY_INFO);
                        });

        return currencyInfoDbRepository.save(new CurrencyInfo(countryInfo.getCountryInfoId(), newCurrencyInfoDTO.currency(), newCurrencyInfoDTO.exchangeRate()));
    }

    @Transactional
    public synchronized CurrencyInfo updatedCurrencyInfo(String id, NewCurrencyInfoDTO updateCurrencyInfoDTO) {
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

    public void deleteCurrencyInfo(String id) {
        currencyInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));
        currencyInfoDbRepository.deleteById(id);
    }

}
