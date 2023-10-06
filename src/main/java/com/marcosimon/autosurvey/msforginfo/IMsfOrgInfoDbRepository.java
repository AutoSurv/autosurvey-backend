package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMsfOrgInfoDbRepository extends JpaRepository<MsfOrgInfo, String> {
    @Query(value = "SELECT O FROM MsfOrgInfo AS O WHERE O.orgName = ?1 and O.countryInfo = ?2")
    Optional<MsfOrgInfo> findByOrgNameAndCountryInfo(String orgName, CountryInfo countryInfo);
}
