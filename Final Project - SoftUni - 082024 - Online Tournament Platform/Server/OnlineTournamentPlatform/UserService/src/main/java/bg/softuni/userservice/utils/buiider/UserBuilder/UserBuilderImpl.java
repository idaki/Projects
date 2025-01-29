package bg.softuni.userservice.utils.buiider.UserBuilder;

import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserProfile;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserBuilderImpl implements UserBuilder {
    private final User user;
    private UserProfile userProfile;
    private UserSecurity userSecurity;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public UserBuilderImpl(BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
    public UserBuilder withRole(String roleInput) {
        Role role = roleRepository.findByName(RoleEnum.valueOf(roleInput));

        if (role == null) {
             role = new Role(RoleEnum.valueOf(roleInput));
            roleRepository.save(role);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        this.user.setRoles(roles);
        return this;
    }

    @Override
    public UserBuilder withPassword(String password) {
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
       User user = new User();
       user.setUsername(this.user.getUsername());
       user.setEmail(this.user.getEmail());
       user.setUserProfile(this.userProfile);
        return this.user;
    }
}
