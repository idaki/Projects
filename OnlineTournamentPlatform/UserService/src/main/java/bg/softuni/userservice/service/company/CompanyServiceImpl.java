package bg.softuni.userservice.service.company;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.repository.CompanyRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
public class CompanyServiceImpl extends CrudServiceImpl<Company, Long> implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }


}
