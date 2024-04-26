package bg.softuni.userservice.repository;




import bg.softuni.userservice.models.entity.business.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findById(long id);
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByUsername(String username);
}
