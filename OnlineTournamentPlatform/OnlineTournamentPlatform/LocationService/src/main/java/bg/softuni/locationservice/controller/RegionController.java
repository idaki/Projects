package bg.softuni.locationservice.controller;

import bg.softuni.locationservice.model.viewDTO.RegionDTO;
import bg.softuni.locationservice.service.RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<RegionDTO> getAllRegions() {
        return regionService.findAllRegions();
    }
}
