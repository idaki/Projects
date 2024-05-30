package bg.softuni.locationservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.locationservice.model.Country;
import bg.softuni.locationservice.model.Region;
import bg.softuni.locationservice.service.CountryService;
import bg.softuni.locationservice.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl extends CrudServiceImpl<Region, Long> implements RegionService {

    @Autowired
    public RegionServiceImpl(JpaRepository<Region, Long> repository) {
        super(repository);
    }


}
