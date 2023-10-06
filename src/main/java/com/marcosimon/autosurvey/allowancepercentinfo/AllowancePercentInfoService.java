package com.marcosimon.autosurvey.allowancepercentinfo;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewAllowancePercentageDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AllowancePercentInfoService {

  private final IAllowancePercentInfoDbRepository allowancePercentInfoDbRepository;
  private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
  public List<AllowancePercentInfo> getAllAllowances() {
    return allowancePercentInfoDbRepository.findAll();
  }


  public AllowancePercentInfo getAllowanceById(String id) {
    return allowancePercentInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
  }

  @Transactional
  public synchronized AllowancePercentInfo addAllowance(NewAllowancePercentageDTO newAllowancePercentageDTO, NewMsfOrgInfoDTO newMsfOrgInfoDTO) {

    MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository.findByOrgNameAndCountryInfo(newMsfOrgInfoDTO.orgName(), newMsfOrgInfoDTO.countryInfo())
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));

    return allowancePercentInfoDbRepository.save(
            new AllowancePercentInfo(msfOrgInfo.getOrgId(),
                    newAllowancePercentageDTO.COLA(),
                    newAllowancePercentageDTO.transportation(),
                    newAllowancePercentageDTO.housing(),
                    newAllowancePercentageDTO.other(),
                    newAllowancePercentageDTO.total()));
  }

  @Transactional
  public synchronized AllowancePercentInfo updateAllowance(String id, NewAllowancePercentageDTO updatedAllowanceValueDTO) {
    AllowancePercentInfo storedAllowanceInfo = allowancePercentInfoDbRepository.findById(id)
            .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));

    if (!Objects.equals(updatedAllowanceValueDTO.COLA(), "")) {
      storedAllowanceInfo.setColAllowancePercent(updatedAllowanceValueDTO.COLA());
    }
    if (!Objects.equals(updatedAllowanceValueDTO.transportation(), "")) {
      storedAllowanceInfo.setTransportationAllowancePercent(updatedAllowanceValueDTO.transportation());
    }
    if (!Objects.equals(updatedAllowanceValueDTO.housing(), "")) {
      storedAllowanceInfo.setHousingAllowancePercent(updatedAllowanceValueDTO.housing());
    }
    if (!Objects.equals(updatedAllowanceValueDTO.other(), "")) {
      storedAllowanceInfo.setOtherAllowancePercent(updatedAllowanceValueDTO.other());
    }

    if (!Objects.equals(updatedAllowanceValueDTO.total(), "")) {
      storedAllowanceInfo.setTotalAllowancePercent(updatedAllowanceValueDTO.total());
    }

    return allowancePercentInfoDbRepository.save(storedAllowanceInfo);

  }


  public void deleteById(String id) {
    allowancePercentInfoDbRepository.findById(id)
            .orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));
    allowancePercentInfoDbRepository.deleteById(id);

  }
}
