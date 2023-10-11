package com.marcosimon.autosurvey.countryinfo;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
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

    public List<CountryInfo> getAllCountryInfo() { return countryInfoDbRepository.findAll(); }

    public CountryInfo getCountryInfoById(String id) {
        return countryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
    }

    public CountryInfo getCountryInfoByNameAndDate(String countryName, String date) {
        return countryInfoDbRepository
                .findByNameAndDate(countryName, date)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized CountryInfo addCountryInfo(NewCountryInfoDTO newCountryInfoDTO) {
        countryInfoDbRepository
                .findByNameAndDate(newCountryInfoDTO.countryName(), newCountryInfoDTO.date())
                        .ifPresent( info -> {
                            throw new CustomException(ALREADY_SAVED_COUNTRY_INFO);
                        });

        return countryInfoDbRepository.save(new CountryInfo(newCountryInfoDTO.countryName(), newCountryInfoDTO.date(), newCountryInfoDTO.currencyRef()));
    }
    @Transactional
    public synchronized CountryInfo updateCountryInfo(String id, NewCountryInfoDTO updateCountryInfo) {
        CountryInfo storedCountryInfo = countryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

        if (updateCountryInfo.countryName() != null && !updateCountryInfo.countryName().isEmpty()) {
            storedCountryInfo.setCountryName(updateCountryInfo.countryName());
        }

        if (updateCountryInfo.date() != null && !updateCountryInfo.date().isEmpty()) {
            storedCountryInfo.setDate(updateCountryInfo.date());
        }

        if (updateCountryInfo.currencyRef() != null && !updateCountryInfo.currencyRef().isEmpty()) {
            storedCountryInfo.setCurrencyRef(updateCountryInfo.currencyRef());
        }

        return countryInfoDbRepository.save(storedCountryInfo);

    }

    public void deleteCountryInfo(String id) {
        countryInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        countryInfoDbRepository.deleteById(id);
    }

}
