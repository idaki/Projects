package bg.softuni.userservice.models.entity.business;

import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "platform_companies")
public class Company extends User {

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public Company(Set<Employee> employees, Set<UserPassword> passwords) {
        this.employees = employees;
    }

    public Company() {
        this.employees = new HashSet<>();
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
