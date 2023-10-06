package com.marcosimon.autosurvey.organization;


import com.marcosimon.autosurvey.models.organization.CreateOrganizationDTO;
import com.marcosimon.autosurvey.models.organization.OrganizationResponseDTO;
import com.marcosimon.autosurvey.models.survey.OrgSurveyDTO;
import com.marcosimon.autosurvey.models.user.UserInfoDto;
import com.marcosimon.autosurvey.user.UserModel;
import com.marcosimon.autosurvey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/organizations")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class OrganizationController {

    private final OrganizationService service;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> listOrganizations() {
        return ResponseEntity.ok(service.getAllOrganizations());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<OrganizationResponseDTO> getOrganization(@PathVariable String id) {
        return ResponseEntity.ok(service.getOrgById(id));
    }

    @GetMapping(path = "{id}/surveys")
    public ResponseEntity<List<OrgSurveyDTO>> getOrgSurveys(@PathVariable String id) {

        return ResponseEntity.ok(service.getOrgSurveys(id));
    }

    @GetMapping(path = "{id}/surveys/{surveyId}")
    public ResponseEntity<OrgSurveyDTO> getOrgSurvey(@PathVariable String surveyId ) {

        return ResponseEntity.ok(service.getOrgSurvey(surveyId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<OrganizationResponseDTO> addOrganization(@RequestBody CreateOrganizationDTO dto, HttpServletRequest req) {
        if (dto.orgName() == null || dto.orgName().equals(""))  return ResponseEntity.badRequest().build();

        UserModel creator = userService.getUserByName(dto.creatorName());
        OrganizationResponseDTO newOrg = service.addOrganization( new Organization(dto.orgName(), creator.getUserId()));

        if (newOrg == null) return ResponseEntity.unprocessableEntity().build();

        URI location = URI.create((req.getRequestURI() + "/" + newOrg.orgId()).replace("//organizations", "/organizations" ));
        return ResponseEntity.created(location).body(newOrg);
    }

    @PatchMapping(path = "{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    ResponseEntity<OrganizationResponseDTO> patchOrganization(@RequestBody CreateOrganizationDTO dto, @PathVariable String id) {
        OrganizationResponseDTO updatedOrg = service.renameOrganization(id, dto.orgName());
        if(updatedOrg == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.accepted().body(updatedOrg);
    }

    @PatchMapping(path = "{id}/manage")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Organization> editUserToOrg(@RequestBody UserInfoDto userInfoDto, @PathVariable String id) {
        OrganizationResponseDTO updatedOrgDto = service.getOrgById(id);
        Organization updatedOrg = new Organization(updatedOrgDto.orgId(), updatedOrgDto.orgName(), updatedOrgDto.surveysIds());

        if(!userInfoDto.status().equals("approved")) {
            updatedOrg = service.addUser(id, userInfoDto.userId());
        }
        if(userInfoDto.status().equals("approved")) {
            updatedOrg = service.deleteUser(id, userInfoDto.userId());
        }


        return ResponseEntity.accepted().body(updatedOrg);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Organization> deleteOrganization(@PathVariable String id) {
        service.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

}
