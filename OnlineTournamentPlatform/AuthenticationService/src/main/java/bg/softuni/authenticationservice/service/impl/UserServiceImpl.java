package bg.softuni.authenticationservice.service.impl;

import java.util.ArrayList;
import java.util.Optional;


import bg.softuni.authenticationservice.UserPasswordRepository;
import bg.softuni.authenticationservice.service.password.UserPasswordService;
import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.password.UserPassword;
import bg.softuni.userservice.repository.CompanyRepository;
import bg.softuni.userservice.repository.ConsumerRepository;
import bg.softuni.userservice.repository.EmployeeRepository;
import bg.softuni.userservice.service.company.CompanyService;
import bg.softuni.userservice.service.consumer.ConsumerService;
import bg.softuni.userservice.service.employee.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService,UserService {

   private final CompanyRepository companyRepository;
   private final EmployeeRepository employeeRepository;
   private final ConsumerRepository consumerRepository;
    private final UserPasswordRepository passwordRepository;


    public UserServiceImpl(ConsumerService consumerService
            , EmployeeService employeeService
            , CompanyService companyService
            , UserPasswordService userPasswordService
            , PasswordEncoder passwordEncoder
            , CompanyRepository companyRepository
            , EmployeeRepository employeeRepository
            , ConsumerRepository consumerRepository
            , UserPasswordRepository passwordRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.consumerRepository = consumerRepository;
        this.passwordRepository = passwordRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Company> optionalCompany = this.companyRepository.findByUsername(username);
        Optional<Employee> optionalEmployee = this.employeeRepository.findByUsername(username);
        Optional<Consumer> optionalConsumer = this.consumerRepository.findByUsername(username);


        Optional<UserPassword> userPassword = null;

        if(optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            userPassword = this.passwordRepository.findByCompany(company);
        }else if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            userPassword = this.passwordRepository.findByEmployee(employee);
        }else if(optionalConsumer.isPresent()) {
            Consumer consumer = optionalConsumer.get();
            userPassword = this.passwordRepository.findByConsumer(consumer);
        }


        if (userPassword.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UserPassword passwordDetails = userPassword.get();
        String hashedPassword = passwordDetails.getHashedPassword();

        Object user = passwordDetails.getConsumer() != null ? passwordDetails.getConsumer() :
                (passwordDetails.getEmployee() != null ? passwordDetails.getEmployee() :
                        passwordDetails.getCompany());

        return new org.springframework.security.core.userdetails.User(
                username,
                hashedPassword,
                new ArrayList<>()
        );

    }






}
