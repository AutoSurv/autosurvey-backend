package com.marcosimon.autosurvey.msforginfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMsfOrgInfoDbRepository extends JpaRepository<MsfOrgInfo, String> {

}
