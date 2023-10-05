package com.marcosimon.autosurvey.countryinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICountryInfoDbRepository extends JpaRepository<CountryInfo, String> {
    @Query(value = "SELECT C FROM CountryInfo AS C WHERE C.countryName = :name and C.date = :date")
    //Do not use column name, use entity variable name
    CountryInfo findByNameAndDate(@Param("name") String name, @Param("date") String date);

    Optional<CountryInfo> findByCountryName(String countryName);


}
