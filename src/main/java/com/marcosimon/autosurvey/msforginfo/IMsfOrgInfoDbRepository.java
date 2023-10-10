package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.models.FinalOrgInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMsfOrgInfoDbRepository extends JpaRepository<MsfOrgInfo, String> {
    @Query(value = "SELECT O FROM MsfOrgInfo AS O WHERE O.orgName = ?1 and O.countryInfo = ?2")
    Optional<MsfOrgInfo> findByOrgNameAndCountryInfo(String orgName, CountryInfo countryInfo);


    @Query(  value = "SELECT new com.marcosimon.autosurvey.models.FinalOrgInfoDTO( C.countryName, C.date, O.orgName, O.orgFullName, F.level, F.functionName," +
            " FS.functionCustomName, C.currencyRef, O.currencyInUse, CUR.exchangeRate, O.workingHours, O.thirteenthSalary, FS.basicSalary," +
            " FS.monthlyAllowance, A.colAllowance, A.transportationAllowance, A.housingAllowance, A.otherAllowance, A.totalAllowance," +
            " AP.colAllowancePercent, AP.transportationAllowancePercent, AP.housingAllowancePercent, AP.otherAllowancePercent," +
            " AP.totalAllowancePercent, FS.tgc) " +
            "FROM MsfOrgInfo O, FunctionSalaryInfo FS, CountryInfo C, FunctionInfo F, CurrencyInfo CUR, AllowanceInfo A, AllowancePercentInfo AP " +
            "WHERE C.countryInfoId = O.countryInfo.countryInfoId " +
            "AND FS.msfOrgInfo.orgId = O.orgId " +
            "AND F.functionInfoId = FS.functionInfo.functionInfoId " +
            "AND C.countryInfoId = CUR.countryInfo.countryInfoId " +
            "AND O.orgId = A.msfOrgInfo.orgId " +
            "AND O.orgId = AP.msfOrgInfo.orgId " +
            "ORDER BY O.orgId")
    List<FinalOrgInfoDTO> findAllFinalOrgInfo();

}
