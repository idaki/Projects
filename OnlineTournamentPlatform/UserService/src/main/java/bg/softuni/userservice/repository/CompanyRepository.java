package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.business.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyById(long i);

    Optional<Company> findByEmail(String email);

    Optional<Object> findByUsername(String username);
}
