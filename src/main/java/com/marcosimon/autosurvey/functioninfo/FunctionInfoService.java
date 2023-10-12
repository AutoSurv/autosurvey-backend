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
                .findByLevelAndFunctionName(newFunctionInfoDTO.level(), newFunctionInfoDTO.function())
                .ifPresent( info -> {
                    throw new CustomException(ALREADY_SAVED_FUNCTION_INFO);
                });

        return functionInfoRepository
                .save(new FunctionInfo(newFunctionInfoDTO.level(), newFunctionInfoDTO.function()));
    }

    @Transactional
    public synchronized FunctionInfo updateFunctionInfo(String id, NewFunctionInfoDTO updateFunctionInfoDTO) {
        FunctionInfo storedFunctionInfo = functionInfoRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));

        if (updateFunctionInfoDTO.level() != null && updateFunctionInfoDTO.level() > 0) {
            storedFunctionInfo.setLevel(updateFunctionInfoDTO.level());
        }
        if (updateFunctionInfoDTO.function() != null && !updateFunctionInfoDTO.function().isEmpty()) {
            storedFunctionInfo.setFunctionName(updateFunctionInfoDTO.function());
        }

        return functionInfoRepository.save(storedFunctionInfo);
    }

    public void deleteFunctionInfo(String id) {
        functionInfoRepository.findById(id).orElseThrow(() -> new CustomException(FUNCTION_INFO_NOT_FOUND));
        functionInfoRepository.deleteById(id);
    }

}
