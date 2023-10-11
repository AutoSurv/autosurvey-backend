package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.allowanceinfo.AllowanceInfo;
import com.marcosimon.autosurvey.allowanceinfo.IAllowanceInfoDbRepository;
import com.marcosimon.autosurvey.allowancepercentinfo.AllowancePercentInfo;
import com.marcosimon.autosurvey.allowancepercentinfo.IAllowancePercentInfoDbRepository;
import com.marcosimon.autosurvey.contactinfo.ContactInfo;
import com.marcosimon.autosurvey.contactinfo.IContactInfoDbRepository;
import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
import com.marcosimon.autosurvey.currencyinfo.CurrencyInfo;
import com.marcosimon.autosurvey.currencyinfo.ICurrencyInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.functioninfo.IFunctionInfoRepository;
import com.marcosimon.autosurvey.functionsalaryinfo.FunctionSalaryInfo;
import com.marcosimon.autosurvey.functionsalaryinfo.IFunctionSalaryInfoDbRepository;
import com.marcosimon.autosurvey.models.FinalOrgInfoDTO;
import com.marcosimon.autosurvey.models.NewCountryInfoDTO;
import com.marcosimon.autosurvey.models.NewFunctionInfoDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MsfOrgInfoService {

  private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
  private final ICountryInfoDbRepository countryInfoDbRepository;
  private final ICurrencyInfoDbRepository currencyInfoDbRepository;
  private final IContactInfoDbRepository contactInfoDbRepository;
  private final IFunctionInfoRepository functionInfoDbRepository;
  private final IFunctionSalaryInfoDbRepository functionSalaryInfoDbRepository;
  private final IAllowanceInfoDbRepository allowanceInfoDbRepository;
  private final IAllowancePercentInfoDbRepository allowancePercentInfoDbRepository;

  public FinalOrgInfoDTO getFinalOrgInfo(String country, String date, String orgName, NewFunctionInfoDTO newFunctionInfo, String functionCustomName) {
    CountryInfo countryInfo = countryInfoDbRepository
            .findByNameAndDate(country, date)
            .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

    CurrencyInfo currencyInfo = currencyInfoDbRepository
            .findById(countryInfo.getCountryInfoId()).orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));

    MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(orgName, countryInfo).orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));

    ContactInfo contactInfo = contactInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));

    FunctionInfo functionInfo = functionInfoDbRepository
            .findByLevelAndFunctionName(newFunctionInfo.level(), newFunctionInfo.function()).orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));

    FunctionSalaryInfo functionSalaryInfo = functionSalaryInfoDbRepository
            .findByFunctionCustomNameAndFunctionAndOrg(functionCustomName, functionInfo, msfOrgInfo)
            .orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));

    AllowanceInfo allowanceInfo = allowanceInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));

    AllowancePercentInfo allowancePercentInfo = allowancePercentInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));

    return new FinalOrgInfoDTO(country, date, orgName, msfOrgInfo.getOrgFullName(), functionInfo.getLevel(), functionInfo.getFunctionName(),
            functionSalaryInfo.getFunctionCustomName(), countryInfo.getCurrencyRef(), msfOrgInfo.getCurrencyInUse(), currencyInfo.getCurrency(), currencyInfo.getExchangeRate(),
            msfOrgInfo.getWorkingHours(), msfOrgInfo.getThirteenthSalary(), functionSalaryInfo.getBasicSalary(), functionSalaryInfo.getMonthlyAllowance(),
            allowanceInfo.getColAllowance(), allowanceInfo.getTransportationAllowance(), allowanceInfo.getHousingAllowance(), allowanceInfo.getOtherAllowance(),
            allowanceInfo.getTotalAllowance(), allowancePercentInfo.getColAllowancePercent(), allowancePercentInfo.getTransportationAllowancePercent(),
            allowancePercentInfo.getHousingAllowancePercent(), allowancePercentInfo.getOtherAllowancePercent(), allowancePercentInfo.getTotalAllowancePercent(),
            functionSalaryInfo.getTgc());

  }

  public List<FinalOrgInfoDTO> getAllFinalOrgInfo() { return msfOrgInfoDbRepository.findAllFinalOrgInfo();}

  public List<MsfOrgInfo> getAllMsfOrgInfo() {
    return Streamable.of(msfOrgInfoDbRepository.findAll()).toList();
  }

  public MsfOrgInfo getMsfOrgInfoById(String id) {
    return msfOrgInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
  }

  @Transactional
  public synchronized MsfOrgInfo addMsfOrgInfo( NewCountryInfoDTO newCountryInfoDTO,
                                                NewMsfOrgInfoDTO newMsfOrgInfoDTO) {
    CountryInfo countryInfo = countryInfoDbRepository
                    .findByNameAndDate(newCountryInfoDTO.countryName(), newCountryInfoDTO.date())
            .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

    msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(newMsfOrgInfoDTO.orgName(), countryInfo)
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

    if (updateMsfOrgInfoDTO.orgFullName() != null && !updateMsfOrgInfoDTO.orgFullName().isEmpty()) {
      storedOrgInfo.setOrgFullName(updateMsfOrgInfoDTO.orgFullName());
    }
    if (updateMsfOrgInfoDTO.orgName() != null && !updateMsfOrgInfoDTO.orgName().isEmpty()) {
      storedOrgInfo.setOrgName(updateMsfOrgInfoDTO.orgName());
    }
    if (updateMsfOrgInfoDTO.workingHours() != null && updateMsfOrgInfoDTO.workingHours() >= 0) {
      storedOrgInfo.setWorkingHours(updateMsfOrgInfoDTO.workingHours());
    }
    if (updateMsfOrgInfoDTO.thirteenthSalary() != null && updateMsfOrgInfoDTO.thirteenthSalary() >= 0) {
      storedOrgInfo.setThirteenthSalary(updateMsfOrgInfoDTO.thirteenthSalary());
    }

    return msfOrgInfoDbRepository.save(storedOrgInfo);
  }

  public void deleteMsfOrgInfo(String id) {
    msfOrgInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
    msfOrgInfoDbRepository.deleteById(id);
  }

}
