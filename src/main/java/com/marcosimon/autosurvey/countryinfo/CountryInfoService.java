package com.marcosimon.autosurvey.countryinfo;

import com.marcosimon.autosurvey.allowanceinfo.IAllowanceInfoDbRepository;
import com.marcosimon.autosurvey.allowancepercentinfo.IAllowancePercentInfoDbRepository;
import com.marcosimon.autosurvey.contactinfo.IContactInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.functionsalaryinfo.IFunctionSalaryInfoDbRepository;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryInfoService {

    private final ICountryInfoDbRepository countryInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final IContactInfoDbRepository contactInfoDbRepository;
    private final IAllowanceInfoDbRepository allowanceInfoDbRepository;
    private final IAllowancePercentInfoDbRepository allowancePercentInfoDbRepository;
    private final IFunctionSalaryInfoDbRepository functionSalaryInfoDbRepository;

    public List<CountryInfo> getAllCountryInfo() { return countryInfoDbRepository.findAll(); }

    public CountryInfo getCountryInfoById(Long id) {
        return countryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
    }

    public CountryInfo getCountryInfoByNameAndDate(String countryName, String year) {
        return countryInfoDbRepository
                .findByNameAndYear(countryName, year)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized CountryInfo addCountryInfo(NewCountryInfoDTO newCountryInfoDTO) {
        countryInfoDbRepository
                .findByNameAndYear(newCountryInfoDTO.countryName(), newCountryInfoDTO.year())
                        .ifPresent( info -> {
                            throw new CustomException(ALREADY_SAVED_COUNTRY_INFO);
                        });

        return countryInfoDbRepository.save(new CountryInfo(newCountryInfoDTO.countryName(), newCountryInfoDTO.isoCode(), newCountryInfoDTO.year(), newCountryInfoDTO.currencyRef()));
    }
    @Transactional
    public synchronized CountryInfo updateCountryInfo(Long id, NewCountryInfoDTO updateCountryInfo) {
        CountryInfo storedCountryInfo = countryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

        if (updateCountryInfo.countryName() != null && !updateCountryInfo.countryName().isEmpty()) {
            storedCountryInfo.setCountryName(updateCountryInfo.countryName());
        }

        if (updateCountryInfo.isoCode() != null && !updateCountryInfo.isoCode().isEmpty()) {
            storedCountryInfo.setIsoCode(updateCountryInfo.isoCode());
        }

        if (updateCountryInfo.year() != null && !updateCountryInfo.year().isEmpty()) {
            storedCountryInfo.setYear(updateCountryInfo.year());
        }

        if (updateCountryInfo.currencyRef() != null && !updateCountryInfo.currencyRef().isEmpty()) {
            storedCountryInfo.setCurrencyRef(updateCountryInfo.currencyRef());
        }

        return countryInfoDbRepository.save(storedCountryInfo);

    }

    public void deleteCountryInfo(Long id) {
        countryInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        msfOrgInfoDbRepository.findAllByCountryInfoId(id).forEach(o -> {
            contactInfoDbRepository.findById(o.getOrgId()).ifPresent(contactInfoDbRepository::delete);
            allowanceInfoDbRepository.findById(o.getOrgId()).ifPresent(allowanceInfoDbRepository::delete);
            allowancePercentInfoDbRepository.findById(o.getOrgId()).ifPresent(allowancePercentInfoDbRepository::delete);
            functionSalaryInfoDbRepository.findAllByOrg(o).forEach(functionSalaryInfoDbRepository::delete);
            msfOrgInfoDbRepository.findById(o.getOrgId()).ifPresent(msfOrgInfoDbRepository::delete);
        });
        countryInfoDbRepository.deleteById(id);
    }

}
