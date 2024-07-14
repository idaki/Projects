package bg.softuni.locationservice.repository;

import bg.softuni.locationservice.model.Country;
import bg.softuni.locationservice.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
