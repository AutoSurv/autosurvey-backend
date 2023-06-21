package com.marcosimon.autosurvey.organization;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationDbRepository extends MongoRepository<Organization, String> {
    Organization findByOrgName(String orgName);



}
