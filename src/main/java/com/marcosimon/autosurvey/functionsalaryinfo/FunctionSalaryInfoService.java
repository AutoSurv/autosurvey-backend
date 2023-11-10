package com.marcosimon.autosurvey.functionsalaryinfo;

import com.marcosimon.autosurvey.benchmarkinfo.BenchmarkInfo;
import com.marcosimon.autosurvey.benchmarkinfo.IBenchmarkInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.functioninfo.IFunctionInfoRepository;
import com.marcosimon.autosurvey.models.NewFunctionSalaryInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FunctionSalaryInfoService {

    private final IFunctionSalaryInfoDbRepository functionSalaryInfoDbRepository;
    private final IBenchmarkInfoDbRepository countryInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final IFunctionInfoRepository functionInfoRepository;

    public List<FunctionSalaryInfo> getAllFunctionSalaryInfo() { return functionSalaryInfoDbRepository.findAll(); }

    public FunctionSalaryInfo getFunctionSalaryInfo(String id) {
        return functionSalaryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));
    }

    /*@Transactional
    public synchronized FunctionSalaryInfo addFunctionSalaryInfo(NewFunctionSalaryInfoDTO newFunctionSalaryInfoDTO) {
        BenchmarkInfo benchmarkInfo = countryInfoDbRepository
                .findByNameAndYear(newFunctionSalaryInfoDTO.countryName(), newFunctionSalaryInfoDTO.year())
                .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findByOrgNameAndCountryInfo(newFunctionSalaryInfoDTO.orgName(), benchmarkInfo)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        FunctionInfo functionInfo = functionInfoRepository
                .findById(newFunctionSalaryInfoDTO.functionId())
                .orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));

        functionSalaryInfoDbRepository
                .findByOrgFunctionIdAndFunctionAndOrg(newFunctionSalaryInfoDTO.orgFunctionId(), functionInfo, msfOrgInfo)
                .ifPresent( info -> {
            throw new CustomException(ALREADY_SAVED_FUNCTION_SALARY_INFO);
        });

        return functionSalaryInfoDbRepository.save(new FunctionSalaryInfo(newFunctionSalaryInfoDTO.orgFunctionId(),
                newFunctionSalaryInfoDTO.orgFunction(),
                newFunctionSalaryInfoDTO.basicSalary(),
                newFunctionSalaryInfoDTO.TGC(),
                newFunctionSalaryInfoDTO.allowancePerFunction(),
                msfOrgInfo, functionInfo));

    }*/

    @Transactional
    public synchronized FunctionSalaryInfo updateFunctionSalaryInfo(String id, NewFunctionSalaryInfoDTO updateFunctionSalaryInfoDTO) {
        FunctionSalaryInfo storedFunctionSalaryInfo = functionSalaryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));

        if (updateFunctionSalaryInfoDTO.orgFunctionId() != null && !updateFunctionSalaryInfoDTO.orgFunctionId().isEmpty()) {
            storedFunctionSalaryInfo.setOrgFunctionId(updateFunctionSalaryInfoDTO.orgFunctionId());
        }
        if (updateFunctionSalaryInfoDTO.orgFunction() != null && !updateFunctionSalaryInfoDTO.orgFunction().isEmpty()) {
            storedFunctionSalaryInfo.setOrgFunction(updateFunctionSalaryInfoDTO.orgFunction());
        }


        return functionSalaryInfoDbRepository.save(storedFunctionSalaryInfo);

    }

    public void deleteFunctionSalaryInfo(String id) {
        functionSalaryInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));
        functionSalaryInfoDbRepository.deleteById(id);
    }

}
