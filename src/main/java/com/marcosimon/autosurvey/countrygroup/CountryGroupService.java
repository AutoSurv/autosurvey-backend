package com.marcosimon.autosurvey.countrygroup;

import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryGroupService {

    @Autowired
    public CountryGroupRepository repo;
    @Autowired
    public AutoSurveyRepository autoSurveyRepository;

    public CountryGroupService() {
    }

    public CountryGroupService(CountryGroupRepository repo, AutoSurveyRepository autoSurveyRepository) {
        this.repo = repo;
        this.autoSurveyRepository = autoSurveyRepository;
    }


    public List<CountryGroup> listCountryGroups() {
        return repo.listCountryGroups();
    }

    public CountryGroup getCountry(String id) {
        return repo.getCountryByID(id);
    }

    public CountryGroup getCountryByName(String name) {
        return repo.getCountryByName(name);
    }

    public CountryGroup saveCountry(CountryGroup country) { return repo.saveCountry(country); }

    public CountryGroup addCountry(CountryGroup country) {
        CountryGroup existingCountry = repo.getCountryByName(country.getCountry());
        if(existingCountry == null) {
            return repo.saveCountry(country);
        }
        return null;
    }

    public CountryGroup renameCountry(String id, String newName) {
        CountryGroup country = repo.getCountryByID(id);
        CountryGroup existingCountry = repo.getCountryByName(newName);
        if(existingCountry == null) {
            country.setCountry(newName);
            return repo.saveCountry(country);
        }
        return null;
    }

    public void deleteCountry(String id) {
        repo.deleteCountryById(id);
    }

    public void deleteCountryByName(String name) {
        repo.deleteCountryByName(name);
    }


}
