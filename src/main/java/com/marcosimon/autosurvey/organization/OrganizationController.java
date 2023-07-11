package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.autosurvey.AutoSurveyService;
import com.marcosimon.autosurvey.models.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("api/organizations")
@CrossOrigin(origins = "*")
public class OrganizationController {

    Logger logger = Logger.getLogger(OrganizationRepository.class.getName());

    @Autowired
    private OrganizationService service;
    @Autowired
    private AutoSurveyService surveyService;

    @GetMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OrganizationResponseDTO>> listOrganizations() {
        return ResponseEntity.ok(service.getAllOrganizations());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<OrganizationResponseDTO> getOrganization(@PathVariable String id) {
        return ResponseEntity.ok(service.getOrgById(id));
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> addOrganization(@RequestBody CreateOrganizationDTO dto, HttpServletRequest req) {
        if (dto.orgName() == null || dto.orgName().equals(""))  return ResponseEntity.badRequest().build();

        //OrganizationResponseDTO newOrg = service.addOrganization( new Organization(UUID.randomUUID().toString(),dto.orgName(), new ArrayList<>()));
        OrganizationResponseDTO newOrg = service.addOrganization( new Organization(dto.orgName()));
        System.out.println(newOrg.orgName());
        if (newOrg == null) return ResponseEntity.unprocessableEntity().build();

        URI location = URI.create((req.getRequestURI() + "/" + newOrg.orgId()));
        return ResponseEntity.created(location).body(newOrg);
    }

    @PatchMapping(path = "{id}")
    ResponseEntity<OrganizationResponseDTO> patchOrganization(@RequestBody CreateOrganizationDTO dto, @PathVariable String id) {
      OrganizationResponseDTO updatedOrg = service.renameOrganization(id, dto.orgName());
      if(updatedOrg == null) return ResponseEntity.badRequest().build();

      return ResponseEntity.accepted().body(updatedOrg);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<Organization> deleteOrganization(@PathVariable String id) {
        service.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

}
