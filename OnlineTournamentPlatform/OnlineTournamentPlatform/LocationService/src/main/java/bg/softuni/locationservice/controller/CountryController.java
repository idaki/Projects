package bg.softuni.locationservice.controller;

import bg.softuni.locationservice.model.viewDTO.CountryDTO;
import bg.softuni.locationservice.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/by-region/{regionId}")
    public List<CountryDTO> getCountriesByRegion(@PathVariable("regionId") String regionId) {
       long id= Long.parseLong(regionId);
        return countryService.findByRegionId(id);
    }
}
