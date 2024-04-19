package bg.softuni.usermodule.repository;

import bg.softuni.usermodule.models.entity.business.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
