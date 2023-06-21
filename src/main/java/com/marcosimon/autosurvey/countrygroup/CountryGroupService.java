package com.marcosimon.autosurvey.countrygroup;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyRepository;
import com.marcosimon.autosurvey.models.AddOrgCountryDTO;
import com.marcosimon.autosurvey.models.CreateCountryDTO;
import com.marcosimon.autosurvey.models.OrgCountryDTO;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryGroupService {

    @Autowired
    public OrganizationRepository organizationRepository;
    @Autowired
    public CountryGroupRepository repo;
    @Autowired
    public AutoSurveyRepository autoSurveyRepository;

    public CountryGroupService() {
    }

    public CountryGroupService(OrganizationRepository organizationRepository, CountryGroupRepository repo, AutoSurveyRepository autoSurveyRepository) {
        this.organizationRepository = organizationRepository;
        this.repo = repo;
        this.autoSurveyRepository = autoSurveyRepository;
    }


    public List<OrgCountryDTO> listCountryGroups() {
        return repo.listCountryGroups().stream().map(CountryConverter::toResponseDto).toList();
    }

    public OrgCountryDTO getCountry(String id) {
        return  CountryConverter.toResponseDto(repo.getCountryByID(id));
    }

    public OrgCountryDTO getCountryByName(String name) {
        return CountryConverter.toResponseDto(repo.getCountryByName(name));
    }

    public OrgCountryDTO addCountry(CreateCountryDTO dto) {
        Organization org = organizationRepository.getById(dto.orgId());
        CountryGroup country = new CountryGroup(dto.country(), new ArrayList<AutoSurvey>() ,dto.orgId(), dto.orgName());
        org.getCountries().add(country);
        org.setCountries(org.getCountries());
        organizationRepository.saveOrganization(org);
        return CountryConverter.toResponseDto(country);

    }

    public OrgCountryDTO renameCountry(String countryId, String newName) {
        CountryGroup country = repo.getCountryByID(countryId);
        country.setCountry(newName);
        repo.saveCountry(country);
        return CountryConverter.toResponseDto(country);

    }

    public void deleteCountry(String id) {
        repo.deleteCountryById(id);
    }

    public void deleteCountryByName(String name) {
        repo.deleteCountryByName(name);
    }


}
