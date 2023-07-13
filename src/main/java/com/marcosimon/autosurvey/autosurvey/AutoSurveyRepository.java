package com.marcosimon.autosurvey.autosurvey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutoSurveyRepository {

    @Autowired
    AutoSurveyDbRepository autoSurveyDbRepository;


    public AutoSurveyRepository() {
    }

    public AutoSurveyRepository(AutoSurveyDbRepository jpaAutoSurveyRepository) {
        this.autoSurveyDbRepository = jpaAutoSurveyRepository;
    }

    public List<AutoSurvey> listSurveys() {

        return Streamable.of(autoSurveyDbRepository.findAll()).toList();
    }

    public List<AutoSurvey> getSurveyByIds(List<String> ids) {return autoSurveyDbRepository.findByIdIn(ids);}

    public AutoSurvey getById(String id) {
        Optional<AutoSurvey> byId = autoSurveyDbRepository.findById(id);
        return byId.orElse(null);
    }

    public AutoSurvey saveSurvey(AutoSurvey autoSurvey) {
        return autoSurveyDbRepository.save(autoSurvey);
    }

    public void saveSurveys(List<AutoSurvey> surveys) {
        autoSurveyDbRepository.saveAll(surveys);
    }

    public void deleteSurvey(String id) {

        autoSurveyDbRepository.deleteById(id);

    }






}
