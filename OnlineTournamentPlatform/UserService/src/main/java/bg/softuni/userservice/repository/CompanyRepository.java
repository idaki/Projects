package bg.softuni.userservice.repository;


import bg.softuni.userservice.models.entity.business.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {


    Optional<Company> findByEmail(String email);


    Optional<Company> findByUsername(String username);

    Company findCompanyById(Long i);
}
