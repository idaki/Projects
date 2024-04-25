package bg.softuni.authenticationservice.service.impl;

import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {


    // Method to save a new password for an employee
    void savePassword(Consumer consumer, String rawPassword);
    void importPasswords();
}