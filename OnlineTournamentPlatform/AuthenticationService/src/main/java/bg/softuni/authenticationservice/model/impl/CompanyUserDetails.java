package bg.softuni.authenticationservice.model.impl;

import bg.softuni.userservice.models.entity.business.company.Company;
import bg.softuni.userservice.models.entity.business.company.PasswordCompany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CompanyUserDetails implements UserDetails {

    private final Company company;
    private final PasswordCompany passwordCompany;

    public CompanyUserDetails(Company company, PasswordCompany passwordCompany) {
        this.company = company;
        this.passwordCompany = passwordCompany;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Populate authorities based on user's roles/permissions
        // For example:
        // return Arrays.asList(new SimpleGrantedAuthority("ROLE_COMPANY"));
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_COMPANY"));
    }

    @Override
    public String getPassword() {
        return passwordCompany.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return company.getUsername();
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
        return company.getActive();
    }


}
