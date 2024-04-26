package bg.softuni.userservice.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface EmployeeService {
    boolean areImported();

    String readUsersFromFile() throws IOException;

    String importUsers() throws IOException;
}
