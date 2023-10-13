package com.marcosimon.autosurvey.functionsalaryinfo;

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
import java.util.Objects;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FunctionSalaryInfoService {

    private final IFunctionSalaryInfoDbRepository functionSalaryInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final IFunctionInfoRepository functionInfoRepository;

    public List<FunctionSalaryInfo> getAllFunctionSalaryInfo() { return functionSalaryInfoDbRepository.findAll(); }

    public FunctionSalaryInfo getFunctionSalaryInfo(Long id) {
        return functionSalaryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized FunctionSalaryInfo addFunctionSalaryInfo(Long orgId, Long functionId, NewFunctionSalaryInfoDTO newFunctionSalaryInfoDTO) {

        MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
                .findById(orgId)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

        FunctionInfo functionInfo = functionInfoRepository
                .findById(functionId)
                .orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));

        functionSalaryInfoDbRepository
                .findByFunctionCustomNameAndFunctionAndOrg(newFunctionSalaryInfoDTO.functionCustomName(), functionInfo, msfOrgInfo)
                .ifPresent( info -> {
            throw new CustomException(ALREADY_SAVED_FUNCTION_SALARY_INFO);
        });

        return functionSalaryInfoDbRepository.save(new FunctionSalaryInfo(newFunctionSalaryInfoDTO.functionCustomName(),
                newFunctionSalaryInfoDTO.basicSalary(),
                newFunctionSalaryInfoDTO.TGC(),
                newFunctionSalaryInfoDTO.monthlyAllowance(),
                msfOrgInfo, functionInfo));

    }

    @Transactional
    public synchronized FunctionSalaryInfo updateFunctionSalaryInfo(Long id, NewFunctionSalaryInfoDTO updateFunctionSalaryInfoDTO) {
        FunctionSalaryInfo storedFunctionSalaryInfo = functionSalaryInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));

        if (updateFunctionSalaryInfoDTO.functionCustomName() != null && !updateFunctionSalaryInfoDTO.functionCustomName().isEmpty()) {
            storedFunctionSalaryInfo.setFunctionCustomName(updateFunctionSalaryInfoDTO.functionCustomName());
        }
        if (updateFunctionSalaryInfoDTO.basicSalary() != null && updateFunctionSalaryInfoDTO.basicSalary() >= 0) {
            storedFunctionSalaryInfo.setBasicSalary(updateFunctionSalaryInfoDTO.basicSalary());
        }
        if (updateFunctionSalaryInfoDTO.monthlyAllowance() != null && updateFunctionSalaryInfoDTO.monthlyAllowance() >= 0) {
            storedFunctionSalaryInfo.setMonthlyAllowance(updateFunctionSalaryInfoDTO.monthlyAllowance());
        }
        if (updateFunctionSalaryInfoDTO.TGC() != null && updateFunctionSalaryInfoDTO.TGC() >= 0) {
            storedFunctionSalaryInfo.setTgc(updateFunctionSalaryInfoDTO.TGC());
        }

        return functionSalaryInfoDbRepository.save(storedFunctionSalaryInfo);

    }

    public void deleteFunctionSalaryInfo(Long id) {
        functionSalaryInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(FUNCTION_SALARY_INFO_NOT_FOUND));
        functionSalaryInfoDbRepository.deleteById(id);
    }

}
