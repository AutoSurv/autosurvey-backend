package com.marcosimon.autosurvey.countrygroup;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryGroupRepository {

    @Autowired
    JpaCountryGroupRepository repo;

    public List<CountryGroup> listCountryGroups() {
        return Streamable.of(repo.findAll()).toList();
    }

    public List<CountryGroup> findGroups(String[] groupNames) {
        return repo.findByCountryIn(groupNames);
    }

    public CountryGroup saveCountry(CountryGroup country) {
        return repo.save(country);
    }

    public CountryGroup getCountryByID(String id) {
        return repo.findById(id).orElse(null);
    }

    public CountryGroup getCountryByName(String name) {
        return repo.findByCountry(name);
    }

    public void deleteCountryById(String id) {
        repo.deleteById(id);
    }

    public void deleteCountryByName(String name) {
        CountryGroup country = getCountryByName(name);
        repo.delete(country);
    }
}
