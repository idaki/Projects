package bg.softuni.userservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;

import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.RoleRepository;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.UserService;
import bg.softuni.userservice.utils.UserDeleteEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenRepository tokenRepository;

    public UserServiceImpl(JpaRepository<User, Long> repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, ApplicationEventPublisher eventPublisher, TokenRepository tokenRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
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
        User user = getNewUser(username, email);
        Role role = roleRepository.findByName(RoleEnum.ADMIN_PROFILE);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        setUserRole(role, user);

        createHashedPassword(password, user);

        return save(user); // Use the save method from CrudServiceImpl
    }

    private static User getNewUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }

    private static void setUserRole(Role role, User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
    }

    private void createHashedPassword(String password, User user) {
        Password passwordEntity = new Password();
        passwordEntity.setPasswordHash(passwordEncoder.encode(password));
        passwordEntity.setUser(user);
        user.setPassword(passwordEntity);
    }

    public UserDetailsExportDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserDetailsExportDTO userDetails = new UserDetailsExportDTO();
        userDetails.setUsername(user.getUsername());
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());
        userDetails.setEmail(user.getEmail());
        return userDetails;
    }



    @Override
    public void deleteUserByUsername(String username) {

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

//            eventPublisher.publishEvent(new UserDeleteEvent(this, user));

            userRepository.delete(user);
        }
    }

    @Override
    public void createSuperAdmin() {
      Optional<User> userOpt = userRepository.findByUsername("super_admin");
       if (userOpt.isPresent()) {
           return;
       }
        User user = getNewUser("super_admin", "super_admin@serdicagrid.com");
        Role role = roleRepository.findByName(RoleEnum.ADMIN_SUPER);

        if (role == null) {
           role= new Role(RoleEnum.ADMIN_SUPER);
            roleRepository.save(role);
        }

        user.setRoles(Set.of(role));
        save(user);
    }


}
