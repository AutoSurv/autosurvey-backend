package com.marcosimon.autosurvey.countrygroup;

import com.marcosimon.autosurvey.models.CountryResponseDTO;
import com.marcosimon.autosurvey.models.CreateCountryDTO;
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
@RequestMapping("api/countrygroups")
public class CountryGroupController {

    Logger logger = Logger.getLogger(CountryGroupRepository.class.getName());

    @Autowired
    private CountryGroupService service;

    @GetMapping
    public ResponseEntity<List<CountryGroup>> listGroups() {
        List<CountryGroup> body = service.listCountryGroups();
        logger.info(body.toString());
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CountryGroup> getCountry(@PathVariable String id) {
        return ResponseEntity.ok(service.getCountry(id));
    }

    @PostMapping
    public ResponseEntity<CountryResponseDTO> addCountry(@RequestBody CreateCountryDTO dto, HttpServletRequest req) {
        CountryGroup country = new CountryGroup(dto.country(), new ArrayList<>());
        CountryGroup newCountry = service.addCountry(country);
        if(newCountry != null){
        URI location = URI.create((req.getRequestURI() + "/" + newCountry.getCountryId()));
        return ResponseEntity.created(location).body(CountryConverter.toResponseDto(newCountry));
        }
        return ResponseEntity.badRequest().build();

    }

    @PatchMapping(path = "{id}")
    ResponseEntity<CountryResponseDTO> patchCountry(@RequestBody CreateCountryDTO dto, @PathVariable String id) {
        CountryGroup updatedCountry = service.renameCountry(id, dto.country());
        if(updatedCountry != null) {
            return ResponseEntity.accepted().body(CountryConverter.toResponseDto(updatedCountry));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<CountryGroup> deleteCountry(@PathVariable String id) {
        service.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }


}
