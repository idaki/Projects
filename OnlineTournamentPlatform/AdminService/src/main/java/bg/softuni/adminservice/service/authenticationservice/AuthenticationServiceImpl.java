package bg.softuni.adminservice.service.authenticationservice;

import bg.softuni.authenticationservice.service.password.UserPasswordService;
import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.repository.CompanyRepository;
import bg.softuni.userservice.repository.ConsumerRepository;
import bg.softuni.userservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AdminAuthenticationService {


    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ConsumerRepository consumerRepository;
    private final UserPasswordService userPasswordService;

    public AuthenticationServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ConsumerRepository consumerRepository, UserPasswordService userPasswordService) {

        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.consumerRepository = consumerRepository;
        this.userPasswordService = userPasswordService;
    }

    @Override
    public void createPasswordsForImports() {
        List<Employee> employees = this.employeeRepository.findAll();

        employees.forEach(e -> {
            String rawPassword = e.getUsername();
            this.userPasswordService.savePassword(e, rawPassword);
        });

        List<Consumer> consumers = this.consumerRepository.findAll();

        consumers.forEach(c -> {
            String rawPassword = c.getUsername();
            this.userPasswordService.savePassword(c, rawPassword);
        });
        List<Company> companies = this.companyRepository.findAll();

        companies.forEach(c -> {
            String rawPassword = c.getUsername();
            this.userPasswordService.savePassword(c, rawPassword);
        });
    }
}
