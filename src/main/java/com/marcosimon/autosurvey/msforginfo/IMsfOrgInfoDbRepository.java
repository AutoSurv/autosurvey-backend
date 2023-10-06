package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMsfOrgInfoDbRepository extends JpaRepository<MsfOrgInfo, String> {

  //Optional<MsfOrgInfo> findByOrgNameAndCountryId(String orgName, CountryInfo countryInfo );
}
