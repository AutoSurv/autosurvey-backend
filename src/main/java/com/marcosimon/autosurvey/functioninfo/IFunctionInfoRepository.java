package com.marcosimon.autosurvey.functioninfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFunctionInfoRepository extends JpaRepository<FunctionInfo, Long> {

    Optional<FunctionInfo> findByLevelAndFunctionName(Integer level, String functionName);

}
