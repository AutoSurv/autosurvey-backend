package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.countrygroup.CountryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaAutoSurveyRepository extends JpaRepository<AutoSurvey, String> {

    //@Query(value = "SELECT s FROM autosurvey s WHERE s.countryGroup in :groups" )
    //List<AutoSurvey> listByCountryGroup(@Param("groups") List<CountryGroup> groups);

}
