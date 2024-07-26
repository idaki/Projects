package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserProfile;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.*;
import bg.softuni.userservice.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenRepository tokenRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final UserProfileRepository userProfileRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, ApplicationEventPublisher eventPublisher, TokenRepository tokenRepository, UserSecurityRepository userSecurityRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
        this.userSecurityRepository = userSecurityRepository;
        this.userProfileRepository = userProfileRepository;
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
        // Create new User
        User user = getNewUser(username, email);

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setUser(user);

        user.setUserProfile(userProfile);
        user = userRepository.save(user);
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUser(user);
        userSecurity = userSecurityRepository.save(userSecurity);
        user.setUserSecurity(userSecurity);

        user = userRepository.save(user);

        Role role = roleRepository.findByName(RoleEnum.ADMIN_PROFILE);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        setUserRole(role, user);
        createHashedPassword(password, user);

        return userRepository.save(user);
    }

    private static UserProfile createUserProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setUser(user); // Set user reference in UserProfile
        return userProfile;
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
        UserSecurity userSecurity = user.getUserSecurity();
        if (userSecurity == null) {
            userSecurity = new UserSecurity();
            userSecurity.setUser(user); // Set the user to userSecurity
            user.setUserSecurity(userSecurity);
        }

        Password passwordEntity = new Password();
        passwordEntity.setPasswordHash(passwordEncoder.encode(password));
        passwordEntity.setUserSecurity(userSecurity); // Set userSecurity to passwordEntity
        userSecurity.setPassword(passwordEntity);

        // Save the user, which will also save userSecurity and password due to cascading
        userRepository.save(user);
    }


    public UserDetailsExportDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserDetailsExportDTO userDetails = new UserDetailsExportDTO();
        userDetails.setUsername(user.getUsername());
        userDetails.setFirstName(user.getUserProfile().getFirstName());
        userDetails.setLastName(user.getUserProfile().getLastName());
        userDetails.setEmail(user.getEmail());
        return userDetails;
    }

    @Override
    public void deleteUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
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
            role = new Role(RoleEnum.ADMIN_SUPER);
            roleRepository.save(role);
        }
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByToken(String jwt) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(jwt);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("Token not found");
        }
        return tokenOpt.get().getUserSecurity().getUser();
    }
}
