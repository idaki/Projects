package bg.softuni.locationservice.service;

import bg.softuni.locationservice.model.viewDTO.CountryDTO;
import bg.softuni.locationservice.model.viewDTO.RegionDTO;

import java.util.List;

public interface RegionService {
    List<RegionDTO> findAllRegions();
}