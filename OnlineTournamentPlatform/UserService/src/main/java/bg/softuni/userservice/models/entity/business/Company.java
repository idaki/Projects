package bg.softuni.userservice.models.entity.business;

import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends User {

    // Other company-specific fields, if any

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees;

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
