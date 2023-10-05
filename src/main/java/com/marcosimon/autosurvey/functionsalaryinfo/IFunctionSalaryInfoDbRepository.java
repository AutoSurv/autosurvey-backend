package com.marcosimon.autosurvey.functionsalaryinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFunctionSalaryInfoDbRepository extends JpaRepository<FunctionSalaryInfo, String> {
}
