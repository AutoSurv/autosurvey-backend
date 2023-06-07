package com.marcosimon.autosurvey.autosurvey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAutoSurveyRepository extends JpaRepository<AutoSurvey, String> {
    List<AutoSurvey> findByCountry(String country);

}
