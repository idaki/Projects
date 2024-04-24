package bg.softuni.authenticationservice.service.impl;

import bg.softuni.authenticationservice.model.impl.EmployeeUserDetails;
import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;
import bg.softuni.userservice.repository.EmployeeRepository;
import bg.softuni.userservice.repository.PasswordEmployeeRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class EmployeeUserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEmployeeRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeUserDetailsServiceImpl(EmployeeRepository employeeRepository,
                                          PasswordEmployeeRepository passwordRepository,
                                          PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findByUsername(username);

        if (employeeOptional.isEmpty()) {
            throw new UsernameNotFoundException("Employee not found");
        }

        Employee employee = employeeOptional.get();
        Optional<PasswordEmployee> passwordEmployeeOptional = passwordRepository.findByUser(employee);

        if (passwordEmployeeOptional.isEmpty()) {
            throw new UsernameNotFoundException("Password not found for employee");
        }

        PasswordEmployee passwordEmployee = passwordEmployeeOptional.get();
        return new EmployeeUserDetails(employee, passwordEmployee);
    }

    // Method to save a new password for an employee
    public void savePassword(Employee employee, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        PasswordEmployee password = new PasswordEmployee();
        password.setHashedPassword(hashedPassword);
        password.setUser(employee);
        passwordRepository.save(password);
    }
}
