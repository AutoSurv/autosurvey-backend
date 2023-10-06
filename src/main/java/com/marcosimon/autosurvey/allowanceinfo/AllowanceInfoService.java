package com.marcosimon.autosurvey.allowanceinfo;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewAllowanceValueDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AllowanceInfoService {

  private final IAllowanceInfoDbRepository allowanceInfoDbRepository;
  private final IMsfOrgInfoDbRepository iMsfOrgInfoDbRepository;

  public List<AllowanceInfo> getAllAllowances() {
    return allowanceInfoDbRepository.findAll();
  }


  public AllowanceInfo getAllowanceById(String id) {
    return allowanceInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
  }

  @Transactional
  public synchronized AllowanceInfo addAllowance(NewAllowanceValueDTO newAllowanceValueDTO, NewMsfOrgInfoDTO newMsfOrgInfoDTO) {

    MsfOrgInfo msfOrgInfo = iMsfOrgInfoDbRepository.findByOrgNameAndCountryInfo(newMsfOrgInfoDTO.orgName(), newMsfOrgInfoDTO.countryInfo())
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));

    return allowanceInfoDbRepository.save(
            new AllowanceInfo(msfOrgInfo.getOrgId(),
                    newAllowanceValueDTO.COLA(),
                    newAllowanceValueDTO.transportation(),
                    newAllowanceValueDTO.housing(),
                    newAllowanceValueDTO.other(),
                    newAllowanceValueDTO.total()));
  }

  @Transactional
  public synchronized AllowanceInfo updateAllowance(String id, NewAllowanceValueDTO updatedAllowanceValueDTO) {
    AllowanceInfo storedAllowanceInfo = allowanceInfoDbRepository.findById(id)
            .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));

    if (!Objects.equals(updatedAllowanceValueDTO.COLA(), "")) {
      storedAllowanceInfo.setColAllowance(updatedAllowanceValueDTO.COLA());
    }
    if (!Objects.equals(updatedAllowanceValueDTO.transportation(), "")) {
      storedAllowanceInfo.setTransportationAllowance(updatedAllowanceValueDTO.transportation());
    }
    if (!Objects.equals(updatedAllowanceValueDTO.housing(), "")) {
      storedAllowanceInfo.setHousingAllowance(updatedAllowanceValueDTO.housing());
    }
    if (!Objects.equals(updatedAllowanceValueDTO.other(), "")) {
      storedAllowanceInfo.setOtherAllowance(updatedAllowanceValueDTO.other());
    }

    if (!Objects.equals(updatedAllowanceValueDTO.total(), "")) {
      storedAllowanceInfo.setTotalAllowance(updatedAllowanceValueDTO.total());
    }

    return allowanceInfoDbRepository.save(storedAllowanceInfo);

  }


  public void deleteById(String id) {
    allowanceInfoDbRepository.findById(id)
            .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
    allowanceInfoDbRepository.deleteById(id);

  }
}
