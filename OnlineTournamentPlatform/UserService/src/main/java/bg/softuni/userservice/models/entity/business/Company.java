package bg.softuni.userservice.models.entity.business;

import bg.softuni.userservice.models.entity.base.User;
import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "platform_companies")
public class Company extends User {

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<UserPassword> passwords;

    public Company(Set<Employee> employees, Set<UserPassword> passwords) {
        this.employees = employees;
        this.passwords = passwords;
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

    public Set<UserPassword> getPasswords() {
        return passwords;
    }

    public void setPasswords(Set<UserPassword> passwords) {
        this.passwords = passwords;
    }
}
