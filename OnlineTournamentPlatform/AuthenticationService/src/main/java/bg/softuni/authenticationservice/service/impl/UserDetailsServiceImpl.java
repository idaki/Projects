package bg.softuni.authenticationservice.service.impl;

import bg.softuni.authenticationservice.UserPasswordRepository;
import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.password.UserPassword;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.CompanyRepository;
import bg.softuni.userservice.repository.ConsumerRepository;
import bg.softuni.userservice.repository.EmployeeRepository;
import bg.softuni.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   private final CompanyRepository companyRepository;
   private final EmployeeRepository employeeRepository;
   private final ConsumerRepository consumerRepository;
    private final UserPasswordRepository passwordRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository, ConsumerRepository consumerRepository, UserPasswordRepository passwordRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.consumerRepository = consumerRepository;
        this.passwordRepository = passwordRepository;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = this.userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOptional.get();

        Optional<UserPassword> passwordOptional = this.passwordRepository.findByUser(user);

        if (passwordOptional.isEmpty()) {
            throw new UsernameNotFoundException("Password not found");
        }

        String hashedPassword= passwordOptional.get().getHashedPassword();

        return new org.springframework.security.core.userdetails.User(
                username,
                hashedPassword,
                new ArrayList<>()
        );

    }






}
