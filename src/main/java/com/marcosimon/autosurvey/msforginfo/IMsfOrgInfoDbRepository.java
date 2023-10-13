package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.models.FinalOrgInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMsfOrgInfoDbRepository extends JpaRepository<MsfOrgInfo, String> {
    @Query(value = "SELECT O FROM MsfOrgInfo AS O WHERE O.orgName = ?1 and O.countryInfo = ?2")
    Optional<MsfOrgInfo> findByOrgNameAndCountryInfo(String orgName, CountryInfo countryInfo);
    @Query(value = "SELECT O FROM MsfOrgInfo AS O WHERE O.countryInfo.countryInfoId = :countryId")
    List<MsfOrgInfo> findAllByCountryInfoId(@Param("countryId")String countryId);
    @Modifying
    @Query(value = "DELETE FROM MsfOrgInfo AS O WHERE O.countryInfo.countryInfoId = :countryId")
    void deleteAllByCountryId(@Param("countryId")String countryId);
    @Query(  value = "SELECT new com.marcosimon.autosurvey.models.FinalOrgInfoDTO( C.countryName, C.date, O.orgName, O.orgFullName, F.level, F.functionName," +
            " FS.functionCustomName, C.currencyRef, O.currencyInUse, CUR.currency, CUR.exchangeRate, O.workingHours, O.thirteenthSalary, FS.basicSalary," +
            " FS.monthlyAllowance, A.colAllowance, A.transportationAllowance, A.housingAllowance, A.otherAllowance, A.totalAllowance," +
            " AP.colAllowancePercent, AP.transportationAllowancePercent, AP.housingAllowancePercent, AP.otherAllowancePercent," +
            " AP.totalAllowancePercent, FS.tgc) " +
            "FROM CountryInfo C " +
            "JOIN MsfOrgInfo O ON C.countryInfoId = O.countryInfo.countryInfoId " +
            "JOIN FunctionSalaryInfo FS ON FS.msfOrgInfo.orgId = O.orgId " +
            "JOIN FunctionInfo F ON F.functionInfoId = FS.functionInfo.functionInfoId "+
            "LEFT JOIN CurrencyInfo CUR ON (C.countryInfoId = CUR.countryInfo.countryInfoId AND O.currencyInfo.currencyInfoId = CUR.currencyInfoId) " +
            "JOIN AllowanceInfo A ON O.orgId = A.msfOrgInfo.orgId " +
            "JOIN AllowancePercentInfo AP ON O.orgId = AP.msfOrgInfo.orgId " +
            "ORDER BY O.orgId ")
    List<FinalOrgInfoDTO> findAllFinalOrgInfo();


}
