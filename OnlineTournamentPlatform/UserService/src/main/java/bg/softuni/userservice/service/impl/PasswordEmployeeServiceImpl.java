package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;
import bg.softuni.userservice.repository.PasswordEmployeeRepository;
import bg.softuni.userservice.service.PasswordEmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordEmployeeServiceImpl implements PasswordEmployeeService<Employee> {
    private final PasswordEmployeeRepository passwordRepository;

    public PasswordEmployeeServiceImpl(PasswordEmployeeRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public PasswordEmployee getPasswordEmployee(Employee employee) {
       Optional <PasswordEmployee> passwordEmployee = passwordRepository.findByUser(employee);

        return passwordEmployee.isPresent() ? passwordEmployee.get() : null;
    }



}