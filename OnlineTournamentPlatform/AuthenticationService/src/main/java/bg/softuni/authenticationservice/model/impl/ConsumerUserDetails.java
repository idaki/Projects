package bg.softuni.authenticationservice.model.impl;

import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.consumer.PasswordConsumer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

public class ConsumerUserDetails implements UserDetails {

    private final Consumer consumer;
    private final PasswordConsumer passwordConsumer;

    public ConsumerUserDetails(Consumer consumer, PasswordConsumer passwordConsumer) {
        this.consumer = consumer;
        this.passwordConsumer = passwordConsumer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Populate authorities based on user's roles/permissions
        // For example:
        // return Arrays.asList(new SimpleGrantedAuthority("ROLE_CONSUMER"));
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CONSUMER"));
    }

    @Override
    public String getPassword() {
        return passwordConsumer.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return consumer.getUsername();
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
        // Assuming isActive is a method in Consumer entity to check if the account is active
        return consumer.getActive();
    }
}
