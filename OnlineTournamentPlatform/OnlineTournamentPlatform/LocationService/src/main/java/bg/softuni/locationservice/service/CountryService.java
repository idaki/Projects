package bg.softuni.locationservice.service;

import bg.softuni.locationservice.model.viewDTO.CountryDTO;


import java.util.List;


public interface CountryService {
    List<CountryDTO> findByRegionId(Long regionId);
}