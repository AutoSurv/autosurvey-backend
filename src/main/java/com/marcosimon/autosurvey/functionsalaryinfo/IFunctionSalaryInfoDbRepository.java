package com.marcosimon.autosurvey.functionsalaryinfo;

import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFunctionSalaryInfoDbRepository extends JpaRepository<FunctionSalaryInfo, Long> {
    @Query(value = "SELECT F FROM FunctionSalaryInfo AS F " +
            "JOIN FunctionInfo AS FI ON F.functionInfo = ?2 " +
            "JOIN MsfOrgInfo AS O ON F.msfOrgInfo = ?3 " +
            "WHERE F.orgFunctionId = ?1")
    Optional<FunctionSalaryInfo> findByOrgFunctionIdAndFunctionAndOrg(String orgFunctionId, FunctionInfo functionInfo, MsfOrgInfo msfOrgInfo);
    @Query(value = "SELECT F FROM FunctionSalaryInfo AS F WHERE F.msfOrgInfo = :org")
    List<FunctionSalaryInfo> findAllByOrg(@Param("org")MsfOrgInfo org);
}

