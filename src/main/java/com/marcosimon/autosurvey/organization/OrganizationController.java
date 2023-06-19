package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyService;
import com.marcosimon.autosurvey.countrygroup.CountryConverter;
import com.marcosimon.autosurvey.countrygroup.CountryGroup;
import com.marcosimon.autosurvey.countrygroup.CountryGroupService;
import com.marcosimon.autosurvey.models.*;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Controller
@RequestMapping("api/organizations")
public class OrganizationController {

    Logger logger = Logger.getLogger(OrganizationRepository.class.getName());

    @Autowired
    private OrganizationService service;
    @Autowired
    private CountryGroupService countryGroupService;
    @Autowired
    private AutoSurveyService surveyService;

    @GetMapping
    public ResponseEntity<List<Organization>> listOrganizations() {
        List<Organization> body = service.getAllOrganizations();
        logger.info(body.toString());
        return ResponseEntity.ok(body);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Organization> getOrganization(@PathVariable String id) {
        return ResponseEntity.ok(service.getOrgById(id));
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> addOrganization(@RequestBody CreateOrganizationDTO dto, HttpServletRequest req) {
        Organization organization = new Organization(dto.orgName(), new ArrayList<CountryGroup>());
        Organization newOrg = service.addOrganization(organization);
        URI location = URI.create((req.getRequestURI() + "/" + newOrg.getOrgId()));
        return ResponseEntity.created(location).body(OrganizationConverter.toResponseDto(newOrg));
    }
    /*
    @PostMapping(path = "{id}/countries")
    public ResponseEntity<OrganizationResponseDTO> addCountry(@PathVariable String id, @RequestBody AddOrgCountryDTO dto, HttpServletRequest req){
        Organization org = service.getOrgById(id);
        CountryGroup newCountry = new CountryGroup(dto.country(), new ArrayList<AutoSurvey>());

        try {

            Organization updatedOrg = service.addCountry(id, newCountry);
            return ResponseEntity.accepted().body(OrganizationConverter.toResponseDto(updatedOrg));

        } catch(NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

     */
    @PostMapping(path = "{id}/countries")
    public ResponseEntity<OrganizationResponseDTO> addCountry(@PathVariable String id, @RequestBody AddOrgCountryDTO dto, HttpServletRequest req){
        Organization org = service.getOrgById(id);
        if(countryGroupService.getCountryByName(dto.country()) != null) return ResponseEntity.badRequest().build();
        CountryGroup newCountry = new CountryGroup(dto.country(), new ArrayList<AutoSurvey>());
        newCountry.setOrganization(org);
        countryGroupService.addCountry(newCountry);
        org.getCountries().add(newCountry);
        org.setCountries(org.getCountries());
        service.organizationRepository.saveOrganization(org);
        URI location = URI.create((req.getRequestURI() + "/" + newCountry.getCountryId()));
        return ResponseEntity.created(location).body(OrganizationConverter.toResponseDto(org));

    }

    @PostMapping(path = "{id}/countries/{countryId}")
    public ResponseEntity<OrganizationResponseDTO> addSurvey(@PathVariable String id, @PathVariable String countryId, @RequestBody CreateSurveyDTO dto, HttpServletRequest req) {
        Organization org = service.getOrgById(id);
        CountryGroup country = countryGroupService.getCountry(countryId);
        if(!country.getOrganization().getOrgId().equals(org.getOrgId()) || !country.getCountry().equals(dto.country())) return ResponseEntity.badRequest().build();
        AutoSurvey newSurvey = new AutoSurvey(  dto.country(),
                                                dto.rent(),
                                                dto.utilities(),
                                                dto.food(),
                                                dto.basicItems(),
                                                dto.transportation(),
                                                dto.educationTotal(),
                                                dto.educationSupplies(),
                                                dto.educationFee(),
                                                dto.educationType(),
                                                dto.accommodationType(),
                                                dto.profession(),
                                                dto.locationGiven(),
                                                dto.locationClustered(),
                                                dto.numResidents(),
                                                dto.numIncomes(),
                                                dto.numFullIncomes(),
                                                dto.numChildren(),
                                                dto.totalIncome(),
                                                dto.comments());
            newSurvey.setCountryGroup(country);
            surveyService.saveSurvey(newSurvey);
            country.getSurveys().add(newSurvey);
            country.setSurveys(country.getSurveys());
            countryGroupService.saveCountry(country);
            URI location = URI.create((req.getRequestURI() + "/" + newSurvey.getId()));
            return ResponseEntity.created(location).body(OrganizationConverter.toResponseDto(org));

    }

    @PatchMapping(path = "{id}")
    ResponseEntity<OrganizationResponseDTO> patchOrganization(@RequestBody CreateOrganizationDTO dto, @PathVariable String id) {
      Organization updatedOrg = service.renameOrganization(id, dto.orgName());
      if(updatedOrg != null) {
          return ResponseEntity.accepted().body(OrganizationConverter.toResponseDto(updatedOrg));
      }
      return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<Organization> deleteOrganization(@PathVariable String id) {
        service.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

}
