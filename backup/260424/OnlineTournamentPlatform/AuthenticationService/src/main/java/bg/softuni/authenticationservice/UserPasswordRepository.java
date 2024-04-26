package bg.softuni.authenticationservice;

import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.password.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Integer> {
    Optional<UserPassword> findByCompany(Company company);

    Optional<UserPassword> findByEmployee(Employee employee);

    Optional<UserPassword> findByConsumer(Consumer consumer);
}
