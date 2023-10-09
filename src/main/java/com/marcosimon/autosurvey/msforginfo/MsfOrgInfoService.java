package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MsfOrgInfoService {

  private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
  private final ICountryInfoDbRepository countryInfoDbRepository;

  public List<MsfOrgInfo> getAllMsfOrgInfo() {
    return msfOrgInfoDbRepository.findAll();
  }

  public MsfOrgInfo getMsfOrgInfoById(String id) {
    return msfOrgInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
  }

  @Transactional //You need country name and date to get country id/ orgInfo
  public synchronized MsfOrgInfo addMsfOrgInfo( NewCountryInfoDTO newCountryInfoDTO,
                                                NewMsfOrgInfoDTO newMsfOrgInfoDTO) {
    CountryInfo countryInfo = Optional.of(countryInfoDbRepository
                    .findByNameAndDate(newCountryInfoDTO.countryName(), newCountryInfoDTO.date()))
            .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

    Optional.of(msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(newMsfOrgInfoDTO.orgName(), countryInfo))
            .ifPresent( info -> {
              throw new CustomException(ALREADY_SAVED_ORGANIZATION);
            });

    return msfOrgInfoDbRepository.save(new MsfOrgInfo(newMsfOrgInfoDTO.orgFullName(),
            newMsfOrgInfoDTO.orgName(),
            newMsfOrgInfoDTO.workingHours(),
            newMsfOrgInfoDTO.thirteenthSalary(),
            newMsfOrgInfoDTO.currencyInUse(),
            countryInfo));
  }

  @Transactional
  public synchronized MsfOrgInfo updateMsfOrgInfo(String id, NewMsfOrgInfoDTO updateMsfOrgInfoDTO) {
    MsfOrgInfo storedOrgInfo = msfOrgInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));

    if (!Objects.equals(updateMsfOrgInfoDTO.orgFullName(), "")) {
      storedOrgInfo.setOrgFullName(updateMsfOrgInfoDTO.orgFullName());
    }

    if (!Objects.equals(updateMsfOrgInfoDTO.orgName(), "")) {
      storedOrgInfo.setOrgName(updateMsfOrgInfoDTO.orgName());
    }

    if (!Objects.equals(updateMsfOrgInfoDTO.workingHours(), "")) {
      storedOrgInfo.setWorkingHours(updateMsfOrgInfoDTO.workingHours());
    }

    if (!Objects.equals(updateMsfOrgInfoDTO.thirteenthSalary(), "")) {
      storedOrgInfo.setThirteenthSalary(updateMsfOrgInfoDTO.thirteenthSalary());
    }

    return msfOrgInfoDbRepository.save(storedOrgInfo);
  }

  public void deleteMsfOrgInfo(String id) {
    msfOrgInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
    msfOrgInfoDbRepository.deleteById(id);
  }

}
