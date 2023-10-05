package com.marcosimon.autosurvey.contactinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactInfoDbRepository extends JpaRepository<ContactInfo, String> {
}
