package com.marcosimon.autosurvey.countrygroup;

import com.marcosimon.autosurvey.models.CountryResponseDTO;
import com.marcosimon.autosurvey.models.CreateCountryDTO;
import com.marcosimon.autosurvey.models.OrgCountryDTO;
import com.marcosimon.autosurvey.models.renameCountryDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("api/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryGroupController {

    Logger logger = Logger.getLogger(CountryGroupRepository.class.getName());

    @Autowired
    private CountryGroupService service;

    @GetMapping
    public ResponseEntity<List<OrgCountryDTO>> listGroups() {
        List<OrgCountryDTO> body = service.listCountryGroups();
        logger.info(body.toString());
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<OrgCountryDTO> getCountry(@PathVariable String id) {
        return ResponseEntity.ok(service.getCountry(id));
    }

    @PostMapping
    public ResponseEntity<OrgCountryDTO> addCountry(@RequestBody CreateCountryDTO dto, HttpServletRequest req) {
        OrgCountryDTO newCountry = service.addCountry(dto);
        URI location = URI.create((req.getRequestURI() + "/" + newCountry.countryId()));
        return ResponseEntity.created(location).body(newCountry);

    }

    @PatchMapping(path = "{id}")
    ResponseEntity<OrgCountryDTO> patchCountry(@RequestBody renameCountryDTO dto, @PathVariable String id) {
        OrgCountryDTO updatedCountry = service.renameCountry(id, dto.name());

        if (updatedCountry == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.accepted().body(updatedCountry);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity deleteCountry(@PathVariable String id) {
        service.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }


}
