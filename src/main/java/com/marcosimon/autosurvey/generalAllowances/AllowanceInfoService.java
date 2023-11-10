package com.marcosimon.autosurvey.generalAllowances;

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

    public List<GeneralAllowances> getAllAllowance() { return allowanceInfoDbRepository.findAll(); }

    public GeneralAllowances getAllowanceById(String id) {
        return allowanceInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
    }

    /*@Transactional
    public synchronized GeneralAllowances addAllowanceInfo(NewAllowanceValueDTO newAllowanceValueDTO) {
        BenchmarkInfo benchmarkInfo = countryInfoDbRepository.findByNameAndYear(newAllowanceValueDTO.countryName(), newAllowanceValueDTO.year())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newAllowanceValueDTO.orgName(), benchmarkInfo)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        allowanceInfoDbRepository
                .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_ALLOWANCE_INFO);
                });

        GeneralAllowances generalAllowances = new GeneralAllowances();
        generalAllowances.setMsfOrgInfo(msfOrgInfo);
        generalAllowances.setColAllowance(newAllowanceValueDTO.colAllowance());
        generalAllowances.setTransportationAllowance(newAllowanceValueDTO.transportation());
        generalAllowances.setHousingAllowance(newAllowanceValueDTO.housing());
        generalAllowances.setCommunicationAllowance(newAllowanceValueDTO.communication());
        generalAllowances.setFoodAllowance(newAllowanceValueDTO.food());
        generalAllowances.setHolidayAllowance(newAllowanceValueDTO.holiday());
        generalAllowances.setReligiousAllowance(newAllowanceValueDTO.religious());
        generalAllowances.setEndOfYearAllowance(newAllowanceValueDTO.endOfYear());
        generalAllowances.setMedicalAllowance(newAllowanceValueDTO.medical());
        generalAllowances.setFamilyAllowance(newAllowanceValueDTO.family());
        generalAllowances.setEducationAllowance(newAllowanceValueDTO.education());
        generalAllowances.setHardshipAllowance(newAllowanceValueDTO.hardship());
        generalAllowances.setDangerAllowance(newAllowanceValueDTO.danger());
        generalAllowances.setLocationAllowance(newAllowanceValueDTO.location());
        generalAllowances.setOtherAllowance(newAllowanceValueDTO.other());
        generalAllowances.setTotalAllowance(newAllowanceValueDTO.total());

        msfOrgInfo.setAllowance(generalAllowances);
        msfOrgInfoDbRepository.save(msfOrgInfo);

        return allowanceInfoDbRepository.save(generalAllowances);
    }*/

    @Transactional
    public synchronized GeneralAllowances updateAllowanceInfo(String id, NewAllowanceValueDTO updateAllowanceValueDTO) {
        GeneralAllowances storedGeneralAllowances = allowanceInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));

       /* if (updateAllowanceValueDTO.colAllowance() != null && updateAllowanceValueDTO.colAllowance() >= 0) {
            storedGeneralAllowances.setColAllowance(updateAllowanceValueDTO.colAllowance());
        }
        if (updateAllowanceValueDTO.transportation() != null && updateAllowanceValueDTO.transportation() >= 0) {
            storedGeneralAllowances.setTransportationAllowance(updateAllowanceValueDTO.transportation());
        }
        if (updateAllowanceValueDTO.housing() != null && updateAllowanceValueDTO.housing() >= 0) {
            storedGeneralAllowances.setHousingAllowance(updateAllowanceValueDTO.housing());
        }
        if (updateAllowanceValueDTO.communication() != null && updateAllowanceValueDTO.communication() >= 0) {
            storedGeneralAllowances.setCommunicationAllowance(updateAllowanceValueDTO.communication());
        }
        if (updateAllowanceValueDTO.food() != null && updateAllowanceValueDTO.food() >= 0) {
            storedGeneralAllowances.setFoodAllowance(updateAllowanceValueDTO.food());
        }
        if (updateAllowanceValueDTO.holiday() != null && updateAllowanceValueDTO.holiday() >= 0) {
            storedGeneralAllowances.setHolidayAllowance(updateAllowanceValueDTO.holiday());
        }
        if (updateAllowanceValueDTO.religious() != null && updateAllowanceValueDTO.religious() >= 0) {
            storedGeneralAllowances.setReligiousAllowance(updateAllowanceValueDTO.religious());
        }
        if (updateAllowanceValueDTO.endOfYear() != null && updateAllowanceValueDTO.endOfYear() >= 0) {
            storedGeneralAllowances.setEndOfYearAllowance(updateAllowanceValueDTO.endOfYear());
        }
        if (updateAllowanceValueDTO.medical() != null && updateAllowanceValueDTO.medical() >= 0) {
            storedGeneralAllowances.setMedicalAllowance(updateAllowanceValueDTO.medical());
        }
        if (updateAllowanceValueDTO.family() != null && updateAllowanceValueDTO.family() >= 0) {
            storedGeneralAllowances.setFamilyAllowance(updateAllowanceValueDTO.family());
        }
        if (updateAllowanceValueDTO.education() != null && updateAllowanceValueDTO.education() >= 0) {
            storedGeneralAllowances.setEducationAllowance(updateAllowanceValueDTO.education());
        }
        if (updateAllowanceValueDTO.hardship() != null && updateAllowanceValueDTO.hardship() >= 0) {
            storedGeneralAllowances.setHardshipAllowance(updateAllowanceValueDTO.hardship());
        }
        if (updateAllowanceValueDTO.danger() != null && updateAllowanceValueDTO.danger() >= 0) {
            storedGeneralAllowances.setDangerAllowance(updateAllowanceValueDTO.danger());
        }
        if (updateAllowanceValueDTO.location() != null && updateAllowanceValueDTO.location() >= 0) {
            storedGeneralAllowances.setLocationAllowance(updateAllowanceValueDTO.location());
        }
        if (updateAllowanceValueDTO.other() != null && updateAllowanceValueDTO.other() >= 0) {
            storedGeneralAllowances.setOtherAllowance(updateAllowanceValueDTO.other());
        }
        if (updateAllowanceValueDTO.total() != null && updateAllowanceValueDTO.total() >= 0) {
            storedGeneralAllowances.setTotalAllowance(updateAllowanceValueDTO.total());
        }*/

        return allowanceInfoDbRepository.save(storedGeneralAllowances);
    }

    public void deleteAllowanceInfo(String id) {
        allowanceInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
        allowanceInfoDbRepository.deleteById(id);
    }



}
