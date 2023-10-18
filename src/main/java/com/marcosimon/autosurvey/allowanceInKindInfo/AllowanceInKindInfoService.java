package com.marcosimon.autosurvey.allowanceInKindInfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewAllowanceInKindDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AllowanceInKindInfoService {

    private final IAllowanceInKindInfoDbRepository allowanceInKindInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final ICountryInfoDbRepository countryInfoDbRepository;

    public List<AllowanceInKindInfo> getAllAllowanceInKind() { return allowanceInKindInfoDbRepository.findAll(); }

    public AllowanceInKindInfo getAllowanceInKind(Long id) {
        return allowanceInKindInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INKIND_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized AllowanceInKindInfo addAllowanceInKindInfo(NewAllowanceInKindDTO newAllowanceInKindDTO) {
        CountryInfo countryInfo = countryInfoDbRepository.findByNameAndYear(newAllowanceInKindDTO.countryName(), newAllowanceInKindDTO.year())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newAllowanceInKindDTO.orgName(), countryInfo)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        allowanceInKindInfoDbRepository
                .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
                    throw new CustomException(ALLOWANCE_INKIND_INFO_NOT_FOUND);
                });

        AllowanceInKindInfo allowanceInKindInfo = new AllowanceInKindInfo();
        allowanceInKindInfo.setMsfOrgInfo(msfOrgInfo);
        allowanceInKindInfo.setColAllowanceInKind(newAllowanceInKindDTO.colAllowance());
        allowanceInKindInfo.setHousingAllowanceInKind(newAllowanceInKindDTO.housing());
        allowanceInKindInfo.setTransportationAllowanceInKind(newAllowanceInKindDTO.transportation());
        allowanceInKindInfo.setCommunicationAllowanceInKind(newAllowanceInKindDTO.communication());
        allowanceInKindInfo.setFoodAllowanceInKind(newAllowanceInKindDTO.food());
        allowanceInKindInfo.setHolidayAllowanceInKind(newAllowanceInKindDTO.holiday());
        allowanceInKindInfo.setReligiousAllowanceInKind(newAllowanceInKindDTO.religious());
        allowanceInKindInfo.setEndOfYearAllowanceInKind(newAllowanceInKindDTO.endOfYear());
        allowanceInKindInfo.setMedicalAllowanceInKind(newAllowanceInKindDTO.medical());
        allowanceInKindInfo.setFamilyAllowanceInKind(newAllowanceInKindDTO.family());
        allowanceInKindInfo.setEducationAllowanceInKind(newAllowanceInKindDTO.education());
        allowanceInKindInfo.setHardshipAllowanceInKind(newAllowanceInKindDTO.hardship());
        allowanceInKindInfo.setDangerAllowanceInKind(newAllowanceInKindDTO.danger());
        allowanceInKindInfo.setLocationAllowanceInKind(newAllowanceInKindDTO.location());
        allowanceInKindInfo.setOtherAllowanceInKind(newAllowanceInKindDTO.other());
        allowanceInKindInfo.setTotalAllowanceInKind(newAllowanceInKindDTO.total());

        msfOrgInfo.setAllowanceInKind(allowanceInKindInfo);
        msfOrgInfoDbRepository.save(msfOrgInfo);

        return allowanceInKindInfoDbRepository.save(allowanceInKindInfo);
    }

    @Transactional
    public synchronized AllowanceInKindInfo updateAllowanceInKindInfo(Long id, NewAllowanceInKindDTO updateAllowanceInKindDTO) {
        AllowanceInKindInfo storedAllowanceInKindInfo = allowanceInKindInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ALLOWANCE_INKIND_INFO_NOT_FOUND));
        if(updateAllowanceInKindDTO.colAllowance() != null && !updateAllowanceInKindDTO.colAllowance().isEmpty()) {
            storedAllowanceInKindInfo.setColAllowanceInKind(updateAllowanceInKindDTO.colAllowance());
        }
        if(updateAllowanceInKindDTO.transportation() != null && !updateAllowanceInKindDTO.transportation().isEmpty()) {
            storedAllowanceInKindInfo.setTransportationAllowanceInKind(updateAllowanceInKindDTO.transportation());
        }
        if(updateAllowanceInKindDTO.housing() != null && !updateAllowanceInKindDTO.housing().isEmpty()) {
            storedAllowanceInKindInfo.setHousingAllowanceInKind(updateAllowanceInKindDTO.housing());
        }
        if(updateAllowanceInKindDTO.communication() != null && !updateAllowanceInKindDTO.communication().isEmpty()) {
            storedAllowanceInKindInfo.setCommunicationAllowanceInKind(updateAllowanceInKindDTO.communication());
        }
        if(updateAllowanceInKindDTO.food() != null && !updateAllowanceInKindDTO.food().isEmpty()) {
            storedAllowanceInKindInfo.setFoodAllowanceInKind(updateAllowanceInKindDTO.food());
        }
        if(updateAllowanceInKindDTO.holiday() != null && !updateAllowanceInKindDTO.holiday().isEmpty()) {
            storedAllowanceInKindInfo.setHolidayAllowanceInKind(updateAllowanceInKindDTO.holiday());
        }
        if(updateAllowanceInKindDTO.religious() != null && !updateAllowanceInKindDTO.religious().isEmpty()) {
            storedAllowanceInKindInfo.setReligiousAllowanceInKind(updateAllowanceInKindDTO.religious());
        }
        if(updateAllowanceInKindDTO.endOfYear() != null && !updateAllowanceInKindDTO.endOfYear().isEmpty()) {
            storedAllowanceInKindInfo.setEndOfYearAllowanceInKind(updateAllowanceInKindDTO.endOfYear());
        }
        if(updateAllowanceInKindDTO.medical() != null && !updateAllowanceInKindDTO.medical().isEmpty()) {
            storedAllowanceInKindInfo.setMedicalAllowanceInKind(updateAllowanceInKindDTO.medical());
        }
        if(updateAllowanceInKindDTO.family() != null && !updateAllowanceInKindDTO.family().isEmpty()) {
            storedAllowanceInKindInfo.setFamilyAllowanceInKind(updateAllowanceInKindDTO.family());
        }
        if(updateAllowanceInKindDTO.education() != null && !updateAllowanceInKindDTO.education().isEmpty()) {
            storedAllowanceInKindInfo.setEducationAllowanceInKind(updateAllowanceInKindDTO.education());
        }
        if(updateAllowanceInKindDTO.hardship() != null && !updateAllowanceInKindDTO.hardship().isEmpty()) {
            storedAllowanceInKindInfo.setHardshipAllowanceInKind(updateAllowanceInKindDTO.hardship());
        }
        if(updateAllowanceInKindDTO.danger() != null && !updateAllowanceInKindDTO.danger().isEmpty()) {
            storedAllowanceInKindInfo.setDangerAllowanceInKind(updateAllowanceInKindDTO.danger());
        }
        if(updateAllowanceInKindDTO.location() != null && !updateAllowanceInKindDTO.location().isEmpty()) {
            storedAllowanceInKindInfo.setLocationAllowanceInKind(updateAllowanceInKindDTO.location());
        }
        if(updateAllowanceInKindDTO.other() != null && !updateAllowanceInKindDTO.other().isEmpty()) {
            storedAllowanceInKindInfo.setOtherAllowanceInKind(updateAllowanceInKindDTO.other());
        }
        if(updateAllowanceInKindDTO.total() != null && !updateAllowanceInKindDTO.total().isEmpty()) {
            storedAllowanceInKindInfo.setTotalAllowanceInKind(updateAllowanceInKindDTO.total());
        }

        return allowanceInKindInfoDbRepository.save(storedAllowanceInKindInfo);
    }

    public void deleteAllowanceInKindInfo(Long id) {
        allowanceInKindInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ALLOWANCE_INKIND_INFO_NOT_FOUND));
        allowanceInKindInfoDbRepository.deleteById(id);
    }
}
