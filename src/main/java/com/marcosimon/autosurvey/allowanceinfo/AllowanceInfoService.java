package com.marcosimon.autosurvey.allowanceinfo;

import com.marcosimon.autosurvey.benchmarkinfo.BenchmarkInfo;
import com.marcosimon.autosurvey.benchmarkinfo.IBenchmarkInfoDbRepository;
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
    private final IBenchmarkInfoDbRepository countryInfoDbRepository;

    public List<AllowanceInfo> getAllAllowance() { return allowanceInfoDbRepository.findAll(); }

    public AllowanceInfo getAllowanceById(Long id) {
        return allowanceInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized AllowanceInfo addAllowanceInfo(NewAllowanceValueDTO newAllowanceValueDTO) {
        BenchmarkInfo benchmarkInfo = countryInfoDbRepository.findByNameAndYear(newAllowanceValueDTO.countryName(), newAllowanceValueDTO.year())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newAllowanceValueDTO.orgName(), benchmarkInfo)
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
        allowanceInfo.setCommunicationAllowance(newAllowanceValueDTO.communication());
        allowanceInfo.setFoodAllowance(newAllowanceValueDTO.food());
        allowanceInfo.setHolidayAllowance(newAllowanceValueDTO.holiday());
        allowanceInfo.setReligiousAllowance(newAllowanceValueDTO.religious());
        allowanceInfo.setEndOfYearAllowance(newAllowanceValueDTO.endOfYear());
        allowanceInfo.setMedicalAllowance(newAllowanceValueDTO.medical());
        allowanceInfo.setFamilyAllowance(newAllowanceValueDTO.family());
        allowanceInfo.setEducationAllowance(newAllowanceValueDTO.education());
        allowanceInfo.setHardshipAllowance(newAllowanceValueDTO.hardship());
        allowanceInfo.setDangerAllowance(newAllowanceValueDTO.danger());
        allowanceInfo.setLocationAllowance(newAllowanceValueDTO.location());
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
        if (updateAllowanceValueDTO.communication() != null && updateAllowanceValueDTO.communication() >= 0) {
            storedAllowanceInfo.setCommunicationAllowance(updateAllowanceValueDTO.communication());
        }
        if (updateAllowanceValueDTO.food() != null && updateAllowanceValueDTO.food() >= 0) {
            storedAllowanceInfo.setFoodAllowance(updateAllowanceValueDTO.food());
        }
        if (updateAllowanceValueDTO.holiday() != null && updateAllowanceValueDTO.holiday() >= 0) {
            storedAllowanceInfo.setHolidayAllowance(updateAllowanceValueDTO.holiday());
        }
        if (updateAllowanceValueDTO.religious() != null && updateAllowanceValueDTO.religious() >= 0) {
            storedAllowanceInfo.setReligiousAllowance(updateAllowanceValueDTO.religious());
        }
        if (updateAllowanceValueDTO.endOfYear() != null && updateAllowanceValueDTO.endOfYear() >= 0) {
            storedAllowanceInfo.setEndOfYearAllowance(updateAllowanceValueDTO.endOfYear());
        }
        if (updateAllowanceValueDTO.medical() != null && updateAllowanceValueDTO.medical() >= 0) {
            storedAllowanceInfo.setMedicalAllowance(updateAllowanceValueDTO.medical());
        }
        if (updateAllowanceValueDTO.family() != null && updateAllowanceValueDTO.family() >= 0) {
            storedAllowanceInfo.setFamilyAllowance(updateAllowanceValueDTO.family());
        }
        if (updateAllowanceValueDTO.education() != null && updateAllowanceValueDTO.education() >= 0) {
            storedAllowanceInfo.setEducationAllowance(updateAllowanceValueDTO.education());
        }
        if (updateAllowanceValueDTO.hardship() != null && updateAllowanceValueDTO.hardship() >= 0) {
            storedAllowanceInfo.setHardshipAllowance(updateAllowanceValueDTO.hardship());
        }
        if (updateAllowanceValueDTO.danger() != null && updateAllowanceValueDTO.danger() >= 0) {
            storedAllowanceInfo.setDangerAllowance(updateAllowanceValueDTO.danger());
        }
        if (updateAllowanceValueDTO.location() != null && updateAllowanceValueDTO.location() >= 0) {
            storedAllowanceInfo.setLocationAllowance(updateAllowanceValueDTO.location());
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
