package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.countrygroup.CountryConverter;
import com.marcosimon.autosurvey.countrygroup.CountryGroup;
import com.marcosimon.autosurvey.countrygroup.CountryGroupRepository;
import com.marcosimon.autosurvey.countrygroup.CountryGroupService;
import com.marcosimon.autosurvey.models.AddOrgCountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    CountryGroupRepository countryGroupRepository;


    public OrganizationService() {
    }

    public OrganizationService(OrganizationRepository organizationRepository, CountryGroupRepository countryGroupRepository) {
        this.organizationRepository = organizationRepository;
        this.countryGroupRepository = countryGroupRepository;
    }


    public List<Organization> getAllOrganizations() {
        return organizationRepository.listOrganizations();
    }

    public Organization getOrgById(String id) {
        return organizationRepository.getById(id);
    }


    public Organization addOrganization(Organization org) {
        Organization existingOrg = organizationRepository.getByOrgName(org.getOrgName());
        if(existingOrg == null) {
            return organizationRepository.saveOrganization(org);
        }
        return null;
    }

    public Organization addCountry(String id, CountryGroup newCountry) {
        Organization org = organizationRepository.getById(id);
            countryGroupRepository.saveCountry(newCountry);
            return organizationRepository.saveOrganization(org);
    }

    public Organization renameOrganization(String id, String name) {
        Organization org = organizationRepository.getById(id);
        Organization existingOrg = organizationRepository.getByOrgName(name);
        if (existingOrg == null) {
            org.setOrgName(name);
            return organizationRepository.saveOrganization(org);
        }
        return  null;
    }

    public void deleteOrganization(String id) {
        organizationRepository.deleteOrganization(id);
    }

    public void deleteOrgByName(String name) {
        organizationRepository.deleteOrgByName(name);
    }

}
