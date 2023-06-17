package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.countrygroup.CountryGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class AutoSurveyRepository {

    Logger log = Logger.getLogger(AutoSurveyRepository.class.getName());
    @Autowired
    JpaAutoSurveyRepository jpaAutoSurveyRepository;


    public AutoSurveyRepository() {
    }

    public AutoSurveyRepository(JpaAutoSurveyRepository jpaAutoSurveyRepository) {
        this.jpaAutoSurveyRepository = jpaAutoSurveyRepository;
    }

    public List<AutoSurvey> listSurveys() {

        return Streamable.of(jpaAutoSurveyRepository.findAll()).toList();
    }

    public AutoSurvey getById(String id) {
        Optional<AutoSurvey> byId = jpaAutoSurveyRepository.findById(id);
        return byId.orElse(null);
    }

    public AutoSurvey saveSurvey(AutoSurvey autoSurvey) {
        return jpaAutoSurveyRepository.save(autoSurvey);
    }

    public void saveSurveys(List<AutoSurvey> surveys) {
        jpaAutoSurveyRepository.saveAll(surveys);
    }

    public void deleteSurvey(String id) {

        jpaAutoSurveyRepository.deleteById(id);

    }
    /*
    public List<AutoSurvey> surveysForCountries(List<CountryGroup> groups) {
        return jpaAutoSurveyRepository.listByCountryGroup(groups);
    }

     */





}
