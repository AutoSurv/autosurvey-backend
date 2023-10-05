package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.constants.ErrorCode;
import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.CountryInfoService;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.ORG_INFO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MsfOrgInfoService {

  private final IMsfOrgInfoDbRepository iMsfOrgInfoDbRepository;
  private final CountryInfoService countryInfoService;

  public List<MsfOrgInfo> getAllMsfOrgInfo() {
    return iMsfOrgInfoDbRepository.findAll();
  }

  public MsfOrgInfo getMsfOrgInfoById(String id) {
    return iMsfOrgInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
  }
  @Transactional
  public synchronized MsfOrgInfo addMsfOrgInfo(NewCountryInfoDTO newMsfOrgInfoDTO) {
    //CountryInfo countryInfo = countryInfoService.addCountryAndCurrencyInfo(newMsfOrgInfoDTO);

    return null;
  }
}
