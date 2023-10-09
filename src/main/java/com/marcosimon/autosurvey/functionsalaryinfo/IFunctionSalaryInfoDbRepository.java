package com.marcosimon.autosurvey.functionsalaryinfo;

import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFunctionSalaryInfoDbRepository extends JpaRepository<FunctionSalaryInfo, String> {
    @Query(value = "SELECT F FROM FunctionSalaryInfo AS F " +
            "JOIN FunctionInfo AS FI ON F.functionInfo = ?2 " +
            "JOIN MsfOrgInfo AS O ON F.msfOrgInfo = ?3" +
            "WHERE F.functionCustomName = ?1")
    Optional<FunctionSalaryInfo> findByFunctionCustomNameAndFunctionIdAndOrgID(String functionCustomName, FunctionInfo functionInfo, MsfOrgInfo msfOrgInfo);
}
