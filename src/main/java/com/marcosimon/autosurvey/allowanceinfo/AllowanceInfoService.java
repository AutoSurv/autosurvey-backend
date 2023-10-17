package com.marcosimon.autosurvey.allowanceinfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewAllowanceValueDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AllowanceInfoService {

    private final IAllowanceInfoDbRepository allowanceInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final ICountryInfoDbRepository countryInfoDbRepository;

    public List<AllowanceInfo> getAllAllowance() { return allowanceInfoDbRepository.findAll(); }

    public AllowanceInfo getAllowanceById(Long id) {
        return allowanceInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized AllowanceInfo addAllowanceInfo(NewAllowanceValueDTO newAllowanceValueDTO) {
        CountryInfo countryInfo = countryInfoDbRepository.findByNameAndYear(newAllowanceValueDTO.countryName(), newAllowanceValueDTO.year())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newAllowanceValueDTO.orgName(), countryInfo)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        allowanceInfoDbRepository
                .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_ALLOWANCE_INFO);
                });

        AllowanceInfo allowanceInfo = new AllowanceInfo();
        allowanceInfo.setMsfOrgInfo(msfOrgInfo);
        allowanceInfo.setColAllowance(newAllowanceValueDTO.colAllowance());
        allowanceInfo.setTransportationAllowance(newAllowanceValueDTO.transportation());
        allowanceInfo.setHousingAllowance(newAllowanceValueDTO.housing());
        allowanceInfo.setOtherAllowance(newAllowanceValueDTO.other());
        allowanceInfo.setTotalAllowance(newAllowanceValueDTO.total());

        msfOrgInfo.setAllowance(allowanceInfo);
        msfOrgInfoDbRepository.save(msfOrgInfo);

        return allowanceInfoDbRepository.save(allowanceInfo);
    }

    @Transactional
    public synchronized AllowanceInfo updateAllowanceInfo(Long id, NewAllowanceValueDTO updateAllowanceValueDTO) {
        AllowanceInfo storedAllowanceInfo = allowanceInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));

        if (updateAllowanceValueDTO.colAllowance() != null && updateAllowanceValueDTO.colAllowance() >= 0) {
            storedAllowanceInfo.setColAllowance(updateAllowanceValueDTO.colAllowance());
        }
        if (updateAllowanceValueDTO.transportation() != null && updateAllowanceValueDTO.transportation() >= 0) {
            storedAllowanceInfo.setTransportationAllowance(updateAllowanceValueDTO.transportation());
        }
        if (updateAllowanceValueDTO.housing() != null && updateAllowanceValueDTO.housing() >= 0) {
            storedAllowanceInfo.setHousingAllowance(updateAllowanceValueDTO.housing());
        }
        if (updateAllowanceValueDTO.other() != null && updateAllowanceValueDTO.other() >= 0) {
            storedAllowanceInfo.setOtherAllowance(updateAllowanceValueDTO.other());
        }
        if (updateAllowanceValueDTO.total() != null && updateAllowanceValueDTO.total() >= 0) {
            storedAllowanceInfo.setTotalAllowance(updateAllowanceValueDTO.total());
        }

        return allowanceInfoDbRepository.save(storedAllowanceInfo);
    }

    public void deleteAllowanceInfo(Long id) {
        allowanceInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
        allowanceInfoDbRepository.deleteById(id);
    }



}
