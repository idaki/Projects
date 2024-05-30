package bg.softuni.locationservice.service.impl;

import bg.softuni.locationservice.model.Country;
import bg.softuni.locationservice.model.viewDTO.CountryDTO;
import bg.softuni.locationservice.repository.CountryRepository;
import bg.softuni.locationservice.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDTO> findByRegionId(Long regionId) {
        List<Country> countries = countryRepository.findAllByRegionId(regionId);
        return countries.stream()
                .map(country -> {
                    CountryDTO countryDTO = new CountryDTO();
                    countryDTO.setName(country.getName());
                    countryDTO.setId(country.getId());
                    return countryDTO;
                })
                .collect(Collectors.toList());
    }
}
