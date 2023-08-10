package com.marcosimon.autosurvey.autosurvey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutoSurveyRepository {

    @Autowired
    AutoSurveyDbRepository autoSurveyDbRepository;

    int pageSize = 10;

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

    public Page<AutoSurvey> getPaginatedSurveys(int page) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return autoSurveyDbRepository.findAll(pageable);
    }

    public Page<AutoSurvey> getSurveysByCountry(int page, String country) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return autoSurveyDbRepository.findAllByCountry(country, pageable);
    }

}
