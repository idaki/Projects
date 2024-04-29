package bg.softuni.userservice.models.entity.business;


import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

@Entity
@Table(name = "platform_employees")
public class Employee extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id", referencedColumnName = "id")
    private UserPassword password;


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserPassword getPassword() {
        return password;
    }

    public void setPassword(UserPassword password) {
        this.password = password;
    }


}