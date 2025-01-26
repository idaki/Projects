package bg.softuni.userservice.utils.events.buiider.UserBuilder;

import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserProfile;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.RoleRepository;
import bg.softuni.userservice.utils.events.buiider.UserBuilder.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserBuilderImpl implements UserBuilder {
    private final User user;
    private UserProfile userProfile;
    private UserSecurity userSecurity;


    public UserBuilderImpl() {

        this.user = new User();
        this.userProfile = new UserProfile();
        this.userSecurity = new UserSecurity();
    }

    @Override
    public UserBuilder withUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.user.setUsername(username);
        return this;
    }

    @Override
    public UserBuilder withEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.user.setEmail(email);
        return this;
    }

    @Override
    public UserBuilder withProfile(String firstName, String lastName, String avatar) {
        this.userProfile.setFirstName(firstName);
        this.userProfile.setLastName(lastName);
        this.userProfile.setAvatar(avatar != null ? avatar : "/assets/avatars/default.png");
        this.userProfile.setUser(this.user);
        this.user.setUserProfile(this.userProfile);
        return this;
    }

    @Override
    public UserBuilder withRole(RoleEnum roleInput) {
        if (roleInput == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        Role role = new Role(roleInput);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        this.user.setRoles(roles);
        return this;
    }

    @Override
    public UserBuilder withPassword(String password, BCryptPasswordEncoder passwordEncoder) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        Password passwordEntity = new Password();
        passwordEntity.setPasswordHash(passwordEncoder.encode(password));

        this.userSecurity.setPassword(passwordEntity);
        passwordEntity.setUserSecurity(this.userSecurity);

        this.userSecurity.setUser(this.user);
        this.user.setUserSecurity(this.userSecurity);
        return this;
    }

    @Override
    public User build() {
        // Ensure relationships are properly set
        if (this.userProfile != null) {
            this.userProfile.setUser(this.user);
        }
        if (this.userSecurity != null) {
            this.userSecurity.setUser(this.user);
        }
        return this.user;
    }
}
