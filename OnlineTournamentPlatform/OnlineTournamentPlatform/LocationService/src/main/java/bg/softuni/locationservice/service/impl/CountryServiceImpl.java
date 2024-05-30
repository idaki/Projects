package bg.softuni.locationservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.locationservice.model.Country;
import bg.softuni.locationservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends CrudServiceImpl<Country, Long> implements CountryService {

    @Autowired
    public CountryServiceImpl(JpaRepository<Country, Long> repository) {
        super(repository);
    }
}
