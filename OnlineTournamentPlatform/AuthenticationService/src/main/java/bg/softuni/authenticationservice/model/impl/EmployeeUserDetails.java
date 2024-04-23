package bg.softuni.authenticationservice.model.impl;

import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class EmployeeUserDetails implements UserDetails {

    private final Employee employee;
    private final PasswordEmployee passwordEmployee;

    public EmployeeUserDetails(Employee employee, PasswordEmployee passwordEmployee) {
        this.employee = employee;
        this.passwordEmployee = passwordEmployee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Populate authorities based on user's roles/permissions
        // For example:
        // return Arrays.asList(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
    }

    @Override
    public String getPassword() {
        return passwordEmployee.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Assuming isActive is a method in Employee entity to check if the account is active
        return employee.getActive();
    }
}
