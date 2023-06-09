package com.marcosimon.autosurvey.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrganizationRepository {

    @Autowired
    JpaOrganizationRepository jpaOrganizationRepository;

    public OrganizationRepository() {
    }

    public OrganizationRepository(JpaOrganizationRepository jpaOrganizationRepository) {
        this.jpaOrganizationRepository = jpaOrganizationRepository;
    }

    public List<Organization> listOrganizations() {
        return Streamable.of(jpaOrganizationRepository.findAll()).toList();
    }

    public Organization getByOrgName(String orgName) {
        return jpaOrganizationRepository.findByOrgName(orgName);
    }

    public Organization getById(String id) {
        Optional<Organization> byId = jpaOrganizationRepository.findById(id);
        return byId.orElse(null);
     }

    public Organization saveOrganization(Organization organization) { return jpaOrganizationRepository.save(organization); }

    public void saveOrganizations(List<Organization> orgs) { jpaOrganizationRepository.saveAll(orgs);}

    public void deleteOrganization(String id) {
        jpaOrganizationRepository.deleteById(id);
    }

}
