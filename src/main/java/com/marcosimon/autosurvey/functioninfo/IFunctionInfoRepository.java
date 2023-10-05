package com.marcosimon.autosurvey.functioninfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFunctionInfoRepository extends JpaRepository<FunctionInfo, String> {
}
