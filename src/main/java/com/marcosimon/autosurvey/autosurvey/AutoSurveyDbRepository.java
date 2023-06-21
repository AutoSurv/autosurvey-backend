package com.marcosimon.autosurvey.autosurvey;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AutoSurveyDbRepository extends MongoRepository<AutoSurvey, String> {

   List<AutoSurvey> findByIdIn(List<String> Ids);

}
