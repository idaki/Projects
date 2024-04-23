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

@Service
public class EmployeeUserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEmployeeRepository passwordRepository;

    public EmployeeUserDetailsServiceImpl(EmployeeRepository employeeRepository, PasswordEmployeeRepository passwordRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordRepository = passwordRepository;
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
}
