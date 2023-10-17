package com.marcosimon.autosurvey.functioninfo;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewFunctionInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.ALREADY_SAVED_FUNCTION_INFO;
import static com.marcosimon.autosurvey.constants.ErrorCode.FUNCTION_INFO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FunctionInfoService {

    private final IFunctionInfoRepository functionInfoRepository;

    public List<FunctionInfo> getAllFunctionInfo() { return functionInfoRepository.findAll(); }

    public FunctionInfo getFunctionInfo(String id) {
        return functionInfoRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized FunctionInfo addFunctionInfo(NewFunctionInfoDTO newFunctionInfoDTO) {
        functionInfoRepository
                .findById(newFunctionInfoDTO.id())
                .ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_FUNCTION_INFO);
                });

        return functionInfoRepository
                .save(new FunctionInfo(newFunctionInfoDTO.id(),
                        newFunctionInfoDTO.msfEntity(),
                        newFunctionInfoDTO.msfProfessionalGroup(),
                        newFunctionInfoDTO.msfLevel(),
                        newFunctionInfoDTO.irffgLevel(),
                        newFunctionInfoDTO.msfJobFamilyDep(),
                        newFunctionInfoDTO.msfFunction()));
    }

    @Transactional
    public synchronized FunctionInfo updateFunctionInfo(String id, NewFunctionInfoDTO updateFunctionInfoDTO) {
        FunctionInfo storedFunctionInfo = functionInfoRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));

        if (updateFunctionInfoDTO.id() != null && !updateFunctionInfoDTO.id().isEmpty()) {
            storedFunctionInfo.setFunctionInfoId(updateFunctionInfoDTO.id());
        }
        if (updateFunctionInfoDTO.msfEntity() != null && !updateFunctionInfoDTO.msfEntity().isEmpty()) {
            storedFunctionInfo.setMsfEntity(updateFunctionInfoDTO.msfEntity());
        }
        if (updateFunctionInfoDTO.msfProfessionalGroup() != null && !updateFunctionInfoDTO.msfProfessionalGroup().isEmpty()) {
            storedFunctionInfo.setMsfProfessionalGroup(updateFunctionInfoDTO.msfProfessionalGroup());
        }
        if (updateFunctionInfoDTO.msfLevel() != null && !updateFunctionInfoDTO.msfLevel().isEmpty()) {
            storedFunctionInfo.setMsfLevel(updateFunctionInfoDTO.msfLevel());
        }
        if (updateFunctionInfoDTO.irffgLevel() != null && !updateFunctionInfoDTO.irffgLevel().isEmpty()) {
            storedFunctionInfo.setIrffgLevel(updateFunctionInfoDTO.irffgLevel());
        }
        if (updateFunctionInfoDTO.msfJobFamilyDep() != null && !updateFunctionInfoDTO.msfJobFamilyDep().isEmpty()) {
            storedFunctionInfo.setMsfJobFamilyDep(updateFunctionInfoDTO.msfJobFamilyDep());
        }
        if (updateFunctionInfoDTO.msfFunction() != null && !updateFunctionInfoDTO.msfFunction().isEmpty()) {
            storedFunctionInfo.setMsfFunction(updateFunctionInfoDTO.msfFunction());
        }

        return functionInfoRepository.save(storedFunctionInfo);
    }

    public void deleteFunctionInfo(String id) {
        functionInfoRepository.findById(id).orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));
        functionInfoRepository.deleteById(id);
    }

}
