package com.marcosimon.autosurvey.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationRepository {

    private final OrganizationDbRepository organizationDbRepository;

    public List<Organization> listOrganizations() {
        return Streamable.of(organizationDbRepository.findAll()).toList();
    }

    public Organization getByOrgName(String orgName) {
        return organizationDbRepository.findByOrgName(orgName);
    }

    public Organization getById(String id) {
        return organizationDbRepository.findById(id).orElse(null);
    }

    public Organization saveOrganization(Organization organization) {
        return organizationDbRepository.save(organization); }

    public void deleteOrganization(String id) {
        organizationDbRepository.deleteById(id);
    }

    public void deleteOrgByName(String name) {
        Organization org = organizationDbRepository.findByOrgName(name);
        organizationDbRepository.delete(org);
    }

}
