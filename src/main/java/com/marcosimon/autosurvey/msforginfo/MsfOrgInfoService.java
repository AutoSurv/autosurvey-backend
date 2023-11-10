package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.generalAllowances.GeneralAllowances;
import com.marcosimon.autosurvey.generalAllowances.IAllowanceInfoDbRepository;
import com.marcosimon.autosurvey.benchmarkinfo.BenchmarkInfo;
import com.marcosimon.autosurvey.benchmarkinfo.IBenchmarkInfoDbRepository;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.functioninfo.IFunctionInfoRepository;
import com.marcosimon.autosurvey.functionsalaryinfo.FunctionSalaryInfo;
import com.marcosimon.autosurvey.functionsalaryinfo.IFunctionSalaryInfoDbRepository;
import com.marcosimon.autosurvey.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MsfOrgInfoService {

  private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
  private final IBenchmarkInfoDbRepository benchmarkInfoDbRepository;
  private final IFunctionInfoRepository functionInfoDbRepository;
  private final IFunctionSalaryInfoDbRepository functionSalaryInfoDbRepository;
  private final IAllowanceInfoDbRepository allowanceInfoDbRepository;

  /*public FinalOrgInfoDTO getFinalOrgInfo(NewFinalOrgInfoDTO newFinalOrgInfoDTO) {
    BenchmarkInfo benchmarkInfo = countryInfoDbRepository
            .findByNameAndYear(newFinalOrgInfoDTO.countryName(), newFinalOrgInfoDTO.year())
            .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));

    CurrencyInfo currencyInfo = currencyInfoDbRepository
            .findById(benchmarkInfo.getCountryInfoId()).orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));

    MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(newFinalOrgInfoDTO.orgName(), benchmarkInfo).orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));

    ContactInfo contactInfo = contactInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));

    FunctionInfo functionInfo = functionInfoDbRepository
            .findById(newFinalOrgInfoDTO.functionId()).orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));

    FunctionSalaryInfo functionSalaryInfo = functionSalaryInfoDbRepository
            .findByOrgFunctionIdAndFunctionAndOrg(newFinalOrgInfoDTO.orgFunctionId(), functionInfo, msfOrgInfo)
            .orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));

    GeneralAllowances generalAllowances = allowanceInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).orElseThrow(() -> new CustomException(ALLOWANCE_INFO_NOT_FOUND));

    AllowancePercentInfo allowancePercentInfo = allowancePercentInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).orElseThrow(() -> new CustomException(ALLOWANCE_PERCENT_INFO_NOT_FOUND));

    return new FinalOrgInfoDTO(benchmarkInfo.getCountryName(), benchmarkInfo.getYear(), msfOrgInfo.getOrgName(), msfOrgInfo.getOrgFullName(), functionInfo.getMsfLevel(), functionInfo.getIrffgLevel(),
            functionInfo.getFunctionInfoId(), functionInfo.getMsfFunction(),functionSalaryInfo.getOrgFunctionId(), functionSalaryInfo.getOrgFunction(), benchmarkInfo.getCurrencyRef(), msfOrgInfo.getCurrencyInUse(), currencyInfo.getCurrency(), currencyInfo.getExchangeRate(),
            msfOrgInfo.getWorkingHours(), msfOrgInfo.getThirteenthSalary(), functionSalaryInfo.getBasicSalary(), functionSalaryInfo.getAllowancePerFunction(),
            generalAllowances.getColAllowance(), generalAllowances.getTransportationAllowance(), generalAllowances.getHousingAllowance(), generalAllowances.getOtherAllowance(),
            generalAllowances.getTotalAllowance(), allowancePercentInfo.getColAllowancePercent(), allowancePercentInfo.getTransportationAllowancePercent(),
            allowancePercentInfo.getHousingAllowancePercent(), allowancePercentInfo.getOtherAllowancePercent(), allowancePercentInfo.getTotalAllowancePercent(),
            functionSalaryInfo.getTgc());

  }*/

  //public List<FinalOrgInfoDTO> getAllFinalOrgInfo() { return msfOrgInfoDbRepository.findAllFinalOrgInfo();}

  public List<MsfOrgInfo> getAllMsfOrgInfo() {
    return Streamable.of(msfOrgInfoDbRepository.findAll()).toList();
  }

  public MsfOrgInfo getMsfOrgInfoById(String id) {
    return msfOrgInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
  }

  /*@Transactional
  public synchronized MsfOrgInfo addMsfOrgInfo(NewMsfOrgInfoDTO newMsfOrgInfoDTO) {
    BenchmarkInfo benchmarkInfo = countryInfoDbRepository
                    .findByNameAndYear(newMsfOrgInfoDTO.countryName(), newMsfOrgInfoDTO.year())
            .orElseThrow(() -> new CustomException(BENCHMARK_INFO_NOT_FOUND));

    msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(newMsfOrgInfoDTO.orgName(), benchmarkInfo)
            .ifPresent( info -> {
              throw new CustomException(ALREADY_SAVED_ORGANIZATION);
            });

    return msfOrgInfoDbRepository.save(new MsfOrgInfo(newMsfOrgInfoDTO.orgFullName(),
            newMsfOrgInfoDTO.orgName(),
            newMsfOrgInfoDTO.orgType(),
            newMsfOrgInfoDTO.dataCollectionDate(),
            newMsfOrgInfoDTO.workingHours(),
            newMsfOrgInfoDTO.thirteenthSalary(),
            newMsfOrgInfoDTO.currencyInUse(),
            benchmarkInfo));
  }*/

  @Transactional
  public synchronized MsfOrgInfo updateMsfOrgInfo(String id, NewMsfOrgInfoDTO updateMsfOrgInfoDTO) {
    MsfOrgInfo storedOrgInfo = msfOrgInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));

    if (updateMsfOrgInfoDTO.orgFullName() != null && !updateMsfOrgInfoDTO.orgFullName().isEmpty()) {
      storedOrgInfo.setOrgFullName(updateMsfOrgInfoDTO.orgFullName());
    }
    if (updateMsfOrgInfoDTO.orgShortName() != null && !updateMsfOrgInfoDTO.orgShortName().isEmpty()) {
      storedOrgInfo.setOrgShortName(updateMsfOrgInfoDTO.orgShortName());
    }
    if (updateMsfOrgInfoDTO.orgType() != null && !updateMsfOrgInfoDTO.orgType().isEmpty()) {
      storedOrgInfo.setOrgType(updateMsfOrgInfoDTO.orgType());
    }
    if (updateMsfOrgInfoDTO.dataCollectionDate() != null && !updateMsfOrgInfoDTO.dataCollectionDate().isEmpty()) {
      storedOrgInfo.setDataCollectionDate(updateMsfOrgInfoDTO.dataCollectionDate());
    }

    return msfOrgInfoDbRepository.save(storedOrgInfo);
  }

  public void deleteMsfOrgInfo(String id) {
    msfOrgInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(ORG_INFO_NOT_FOUND));
    msfOrgInfoDbRepository.deleteById(id);
  }

}
