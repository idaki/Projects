package bg.softuni.usermodule;

import bg.softuni.usermodule.models.entity.business.company.Company;
import bg.softuni.usermodule.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddNewCompanyToDB {

    @Autowired
    private CompanyRepository companyRepository;

    private Company company;

    @Before
    public void setup() {
        this.company = new Company();
        this.company.setUsername("Name2");
        this.company.setEmail("email1@gmail.com");
    }

    @Test
    public void testSaveCompany() {
        assertNotNull("CompanyRepository is null", companyRepository);

        Company savedCompany = companyRepository.saveAndFlush(company);
        assertNotNull("Saved company is null", savedCompany);
        assertNotNull("Saved company ID is null", savedCompany.getId());
    }
}
