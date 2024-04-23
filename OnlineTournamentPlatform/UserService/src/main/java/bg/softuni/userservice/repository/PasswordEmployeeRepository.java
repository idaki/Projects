package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordEmployeeRepository extends JpaRepository<PasswordEmployee, Long> {
    Optional<PasswordEmployee> findByUser(Employee user);
}
