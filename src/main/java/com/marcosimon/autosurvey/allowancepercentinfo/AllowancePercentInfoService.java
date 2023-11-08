package com.marcosimon.autosurvey.allowancepercentinfo;

import com.marcosimon.autosurvey.benchmarkinfo.BenchmarkInfo;
import com.marcosimon.autosurvey.benchmarkinfo.IBenchmarkInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewAllowancePercentageDTO;

import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AllowancePercentInfoService {

    private final IAllowancePercentInfoDbRepository allowancePercentInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final IBenchmarkInfoDbRepository countryInfoDbRepository;

    public List<AllowancePercentInfo> getAllAllowancePercent() { return allowancePercentInfoDbRepository.findAll(); }

    public AllowancePercentInfo getAllowancePercentById(Long id) {
        return allowancePercentInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized AllowancePercentInfo addAllowancePercentInfo(NewAllowancePercentageDTO newAllowancePercentageDTO) {
        BenchmarkInfo benchmarkInfo = countryInfoDbRepository.findByNameAndYear(newAllowancePercentageDTO.countryName(), newAllowancePercentageDTO.year())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newAllowancePercentageDTO.orgName(), benchmarkInfo)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        allowancePercentInfoDbRepository
                .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_ALLOWANCE_PERCENT_INFO);
                } );

        AllowancePercentInfo allowancePercentInfo = new AllowancePercentInfo();
        allowancePercentInfo.setMsfOrgInfo(msfOrgInfo);
        allowancePercentInfo.setColAllowancePercent(newAllowancePercentageDTO.colAllowance());
        allowancePercentInfo.setHousingAllowancePercent(newAllowancePercentageDTO.housing());
        allowancePercentInfo.setTransportationAllowancePercent(newAllowancePercentageDTO.transportation());
        allowancePercentInfo.setCommunicationAllowancePercent(newAllowancePercentageDTO.communication());
        allowancePercentInfo.setFoodAllowancePercent(newAllowancePercentageDTO.food());
        allowancePercentInfo.setHolidayAllowancePercent(newAllowancePercentageDTO.holiday());
        allowancePercentInfo.setReligiousAllowancePercent(newAllowancePercentageDTO.religious());
        allowancePercentInfo.setEndOfYearAllowancePercent(newAllowancePercentageDTO.endOfYear());
        allowancePercentInfo.setMedicalAllowancePercent(newAllowancePercentageDTO.medical());
        allowancePercentInfo.setFamilyAllowancePercent(newAllowancePercentageDTO.family());
        allowancePercentInfo.setEducationAllowancePercent(newAllowancePercentageDTO.education());
        allowancePercentInfo.setHardshipAllowancePercent(newAllowancePercentageDTO.hardship());
        allowancePercentInfo.setDangerAllowancePercent(newAllowancePercentageDTO.danger());
        allowancePercentInfo.setLocationAllowancePercent(newAllowancePercentageDTO.location());
        allowancePercentInfo.setOtherAllowancePercent(newAllowancePercentageDTO.other());
        allowancePercentInfo.setTotalAllowancePercent(newAllowancePercentageDTO.total());

        msfOrgInfo.setAllowancePercent(allowancePercentInfo);
        msfOrgInfoDbRepository.save(msfOrgInfo);

        return allowancePercentInfoDbRepository.save(allowancePercentInfo);
    }

    @Transactional
    public synchronized AllowancePercentInfo updateAllowancePercentInfo(Long id, NewAllowancePercentageDTO updateAllowancePercentageDTO) {
        AllowancePercentInfo storedAllowancePercentInfo = allowancePercentInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));

        if (updateAllowancePercentageDTO.colAllowance() != null && updateAllowancePercentageDTO.colAllowance() >= 0) {
            storedAllowancePercentInfo.setColAllowancePercent(updateAllowancePercentageDTO.colAllowance());
        }
        if (updateAllowancePercentageDTO.transportation() != null && updateAllowancePercentageDTO.transportation() >= 0) {
            storedAllowancePercentInfo.setTransportationAllowancePercent(updateAllowancePercentageDTO.transportation());
        }
        if (updateAllowancePercentageDTO.housing() != null && updateAllowancePercentageDTO.housing() >= 0) {
            storedAllowancePercentInfo.setHousingAllowancePercent(updateAllowancePercentageDTO.housing());
        }
        if (updateAllowancePercentageDTO.communication() != null && updateAllowancePercentageDTO.communication() >= 0) {
            storedAllowancePercentInfo.setCommunicationAllowancePercent(updateAllowancePercentageDTO.communication());
        }
        if (updateAllowancePercentageDTO.food() != null && updateAllowancePercentageDTO.food() >= 0) {
            storedAllowancePercentInfo.setFoodAllowancePercent(updateAllowancePercentageDTO.food());
        }
        if (updateAllowancePercentageDTO.holiday() != null && updateAllowancePercentageDTO.holiday() >= 0) {
            storedAllowancePercentInfo.setHolidayAllowancePercent(updateAllowancePercentageDTO.holiday());
        }
        if (updateAllowancePercentageDTO.religious() != null && updateAllowancePercentageDTO.religious() >= 0) {
            storedAllowancePercentInfo.setReligiousAllowancePercent(updateAllowancePercentageDTO.religious());
        }
        if (updateAllowancePercentageDTO.endOfYear() != null && updateAllowancePercentageDTO.endOfYear() >= 0) {
            storedAllowancePercentInfo.setEndOfYearAllowancePercent(updateAllowancePercentageDTO.endOfYear());
        }
        if (updateAllowancePercentageDTO.medical() != null && updateAllowancePercentageDTO.medical() >= 0) {
            storedAllowancePercentInfo.setMedicalAllowancePercent(updateAllowancePercentageDTO.medical());
        }
        if (updateAllowancePercentageDTO.family() != null && updateAllowancePercentageDTO.family() >= 0) {
            storedAllowancePercentInfo.setFamilyAllowancePercent(updateAllowancePercentageDTO.family());
        }
        if (updateAllowancePercentageDTO.education() != null && updateAllowancePercentageDTO.education() >= 0) {
            storedAllowancePercentInfo.setEducationAllowancePercent(updateAllowancePercentageDTO.education());
        }
        if (updateAllowancePercentageDTO.hardship() != null && updateAllowancePercentageDTO.hardship() >= 0) {
            storedAllowancePercentInfo.setHardshipAllowancePercent(updateAllowancePercentageDTO.hardship());
        }
        if (updateAllowancePercentageDTO.danger() != null && updateAllowancePercentageDTO.danger() >= 0) {
            storedAllowancePercentInfo.setDangerAllowancePercent(updateAllowancePercentageDTO.danger());
        }
        if (updateAllowancePercentageDTO.location() != null && updateAllowancePercentageDTO.location() >= 0) {
            storedAllowancePercentInfo.setLocationAllowancePercent(updateAllowancePercentageDTO.location());
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
