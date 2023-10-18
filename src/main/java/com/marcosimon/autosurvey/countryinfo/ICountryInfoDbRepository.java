package com.marcosimon.autosurvey.countryinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICountryInfoDbRepository extends JpaRepository<CountryInfo, Long> {
    @Query(value = "SELECT C FROM CountryInfo AS C WHERE C.countryName = :name and C.year = :year")
    Optional<CountryInfo> findByNameAndYear(@Param("name") String name, @Param("year") String year);


}
