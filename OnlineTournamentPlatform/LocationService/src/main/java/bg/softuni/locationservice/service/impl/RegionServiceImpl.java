package bg.softuni.locationservice.service.impl;

import bg.softuni.locationservice.model.viewDTO.RegionDTO;
import bg.softuni.locationservice.repository.RegionRepository;
import bg.softuni.locationservice.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<RegionDTO> findAllRegions() {
        return regionRepository.findAll().stream().map(region -> {
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setName(region.getName());
            regionDTO.setId(region.getId());
            return regionDTO;
        }).collect(Collectors.toList());
    }
}