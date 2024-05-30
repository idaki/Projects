package bg.softuni.locationservice.repository;

import bg.softuni.locationservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByRegionId(Long regionId);
}
