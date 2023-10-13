package com.marcosimon.autosurvey.allowancepercentinfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewAllowancePercentageDTO;

import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AllowancePercentInfoService {

    private final IAllowancePercentInfoDbRepository allowancePercentInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final ICountryInfoDbRepository countryInfoDbRepository;

    public List<AllowancePercentInfo> getAllAllowancePercent() { return allowancePercentInfoDbRepository.findAll(); }

    public AllowancePercentInfo getAllowancePercentById(Long id) {
        return allowancePercentInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized AllowancePercentInfo addAllowancePercentInfo(NewAllowancePercentageDTO newAllowancePercentageDTO) {
        CountryInfo countryInfo = countryInfoDbRepository.findByNameAndDate(newAllowancePercentageDTO.countryName(), newAllowancePercentageDTO.date())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newAllowancePercentageDTO.orgName(), countryInfo)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        allowancePercentInfoDbRepository
                .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_ALLOWANCE_PERCENT_INFO);
                } );

        return allowancePercentInfoDbRepository
                .save(new AllowancePercentInfo(msfOrgInfo.getOrgId(), newAllowancePercentageDTO.COLA(), newAllowancePercentageDTO.transportation(), newAllowancePercentageDTO.housing(), newAllowancePercentageDTO.other(), newAllowancePercentageDTO.total()));
    }

    @Transactional
    public synchronized AllowancePercentInfo updateAllowancePercentInfo(Long id, NewAllowancePercentageDTO updateAllowancePercentageDTO) {
        AllowancePercentInfo storedAllowancePercentInfo = allowancePercentInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));

        if (updateAllowancePercentageDTO.COLA() != null && updateAllowancePercentageDTO.COLA() >= 0) {
            storedAllowancePercentInfo.setColAllowancePercent(updateAllowancePercentageDTO.COLA());
        }
        if (updateAllowancePercentageDTO.transportation() != null && updateAllowancePercentageDTO.transportation() >= 0) {
            storedAllowancePercentInfo.setTransportationAllowancePercent(updateAllowancePercentageDTO.transportation());
        }
        if (updateAllowancePercentageDTO.housing() != null && updateAllowancePercentageDTO.housing() >= 0) {
            storedAllowancePercentInfo.setHousingAllowancePercent(updateAllowancePercentageDTO.housing());
        }
        if (updateAllowancePercentageDTO.other() != null && updateAllowancePercentageDTO.other() >= 0) {
            storedAllowancePercentInfo.setOtherAllowancePercent(updateAllowancePercentageDTO.other());
        }
        if (updateAllowancePercentageDTO.total() != null && updateAllowancePercentageDTO.total() >= 0) {
            storedAllowancePercentInfo.setTotalAllowancePercent(updateAllowancePercentageDTO.total());
        }

        return allowancePercentInfoDbRepository.save(storedAllowancePercentInfo);
    }

    public void deleteAllowancePercentInfo(Long id) {
        allowancePercentInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));
        allowancePercentInfoDbRepository.deleteById(id);
    }

}
