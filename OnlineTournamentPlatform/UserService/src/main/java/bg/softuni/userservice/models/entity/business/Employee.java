package bg.softuni.userservice.models.entity.business;


import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

@Entity
@Table(name = "user_employees")
public class Employee extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }



}