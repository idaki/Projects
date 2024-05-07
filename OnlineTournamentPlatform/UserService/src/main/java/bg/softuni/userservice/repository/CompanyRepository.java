package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.business.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyById(int i);
}
