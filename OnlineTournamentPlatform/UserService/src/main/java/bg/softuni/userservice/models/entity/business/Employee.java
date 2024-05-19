package bg.softuni.userservice.models.entity.business;

import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
