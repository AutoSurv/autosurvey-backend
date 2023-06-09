package com.marcosimon.autosurvey.organization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrganizationRepository  extends JpaRepository<Organization, String> {
    Organization findByOrgName(String orgName);
}
