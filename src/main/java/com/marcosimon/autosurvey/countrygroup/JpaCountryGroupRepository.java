package com.marcosimon.autosurvey.countrygroup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCountryGroupRepository extends JpaRepository<CountryGroup, String> {

    CountryGroup findByCountry(String name);
    List<CountryGroup> findByCountryIn(String[] countries);
}
