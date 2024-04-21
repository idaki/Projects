package bg.softuni.usermodule.repository;

import bg.softuni.usermodule.models.entity.business.company.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyById(int i);
}
