package bg.softuni.locationservice.repository;

import bg.softuni.locationservice.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
