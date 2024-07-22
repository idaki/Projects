package bg.softuni.userservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;

import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.RoleRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.user.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(JpaRepository<User, Long> repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public boolean isExistingUser(String username, String email) {
        return findByUsername(username).isPresent()
                || findByEmail(email).isPresent()
                || findByUsername(email).isPresent()
                || findByEmail(username).isPresent();
    }

    @Override
    public void register(String username, String password, String email) {
        if (!isExistingUser(username, email)) {
            registerUser(username, password, email);
        }
    }

    public User registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        // Fetch the ADMIN_PROFILE role
        Role adminProfileRole = roleRepository.findByName(RoleEnum.ADMIN_PROFILE);
        if (adminProfileRole == null) {
            throw new RuntimeException("Role not found");
        }

        // Set the roles for the user
        Set<Role> roles = new HashSet<>();
        roles.add(adminProfileRole);
        user.setRoles(roles);

        // Set the password
        Password passwordEntity = new Password();
        passwordEntity.setPasswordHash(passwordEncoder.encode(password));
        passwordEntity.setUser(user);
        user.setPassword(passwordEntity);

        // Save the user
        return save(user); // Use the save method from CrudServiceImpl
    }
}
