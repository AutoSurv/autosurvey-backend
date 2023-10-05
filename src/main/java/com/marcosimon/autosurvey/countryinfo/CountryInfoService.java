package com.marcosimon.autosurvey.countryinfo;

import com.marcosimon.autosurvey.currencyinfo.CurrencyInfo;
import com.marcosimon.autosurvey.currencyinfo.ICurrencyInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.models.NewCurrencyInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CountryInfoService {

    private final ICountryInfoDbRepository countryInfoDbRepository;
    private final ICurrencyInfoDbRepository currencyInfoDbRepository;

    public List<CountryInfo> getAllCountryInfo() { return countryInfoDbRepository.findAll(); }

    public CountryInfo getCountryInfoById(String id) {
        return countryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
    }
    @Transactional //You need to add country and currency together due to ID
    public synchronized CountryInfo addCountryAndCurrencyInfo(NewCountryInfoDTO newCountryInfoDTO, NewCurrencyInfoDTO newCurrencyInfoDTO) {
        Optional.of(countryInfoDbRepository
                .findByNameAndDate(newCountryInfoDTO.countryName(), newCountryInfoDTO.date()))
                .ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_COUNTRY_INFO);
                });

        CountryInfo newCountryInfo = countryInfoDbRepository.save(new CountryInfo(newCountryInfoDTO.countryName(), newCountryInfoDTO.date(), newCountryInfoDTO.currencyRef()));
        currencyInfoDbRepository.save(new CurrencyInfo(newCountryInfo.getCountryInfoId(), newCurrencyInfoDTO.currency(), newCurrencyInfoDTO.exchangeRate()));
        return newCountryInfo;
    }
    @Transactional
    public synchronized CountryInfo updateCountryInfo(String id, NewCountryInfoDTO updateCountryInfo) {
        CountryInfo storedCountryInfo = countryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

        if (!Objects.equals(updateCountryInfo.countryName(), "")) {
            storedCountryInfo.setCountryName(updateCountryInfo.countryName());
        }

        if (!Objects.equals(updateCountryInfo.date(), "")) {
            storedCountryInfo.setDate(updateCountryInfo.date());
        }

        if (!Objects.equals(updateCountryInfo.currencyRef(), "")) {
            storedCountryInfo.setCurrencyRef(updateCountryInfo.currencyRef());
        }

        return countryInfoDbRepository.save(storedCountryInfo);

    }

    public void deleteCountryInfo(String id) {
        countryInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        countryInfoDbRepository.deleteById(id);
    }

}
