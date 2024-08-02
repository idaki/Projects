package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.*;
import bg.softuni.userservice.models.enums.RoleEnum;

import bg.softuni.userservice.repository.*;
import bg.softuni.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenRepository tokenRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final UserProfileRepository userProfileRepository;


    @Autowired
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

        // Create and set UserProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setAvatar("/assets/avatars/dexter.png");
        userProfile.setUser(user);
        user.setUserProfile(userProfile);
        user = userRepository.save(user);


        // Create and set UserSecurity
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUser(user);
        user.setUserSecurity(userSecurity);
        userSecurity = userSecurityRepository.save(userSecurity);

        // Save user with updated userSecurity
        user = userRepository.save(user);

        // Assign role and hashed password
        Role role = roleRepository.findByName(RoleEnum.ADMIN_USER);
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
        userProfile.setUser(user);
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
            userSecurity.setUser(user);
            user.setUserSecurity(userSecurity);
        }

        Password passwordEntity = new Password();
        passwordEntity.setPasswordHash(passwordEncoder.encode(password));
        passwordEntity.setUserSecurity(userSecurity);
        userSecurity.setPassword(passwordEntity);

        userRepository.save(user);
    }

    public UserDetailsExportDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return getUserDetailsExportDTO(user);
    }

    private static UserDetailsExportDTO getUserDetailsExportDTO(User user) {
        UserDetailsExportDTO userDetails = new UserDetailsExportDTO();
        userDetails.setUsername(user.getUsername());
        userDetails.setFirstName(user.getUserProfile().getFirstName());
        userDetails.setLastName(user.getUserProfile().getLastName());
        userDetails.setEmail(user.getEmail());
        userDetails.setAvatar(user.getUserProfile().getAvatar());
        userDetails.setId(user.getId());
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
    public void InitUser(String roleInput, String password, String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            System.out.println("User " + username + " already exists.");
            return;
        }

        String email = username.toLowerCase() + "@serdicagrid.com";
        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Email " + email + " already exists.");
            return;
        }

        User user = getNewUser(username, email);

        // Create and set UserSecurity
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUser(user);
        user.setUserSecurity(userSecurity);

        // Create and set UserProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setAvatar("/assets/avatars/dexter.png");
        userProfile.setUser(user);
        user.setUserProfile(userProfile);

        // Save user first to generate ID and establish relationships
        user = userRepository.save(user);

        // Save UserSecurity and UserProfile
        userSecurityRepository.save(userSecurity);
        userProfileRepository.save(userProfile);

        // Assign role and hashed password
        Role role = roleRepository.findByName(RoleEnum.valueOf(roleInput));
        if (role == null) {
            role = new Role(RoleEnum.valueOf(roleInput));
            roleRepository.save(role);
        }
        user.setRoles(Set.of(role));
        createHashedPassword(password, user);

        // Finally, save the user again to update references
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

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetailsExportDTO findUserByDetails(String username, String firstName, String lastName) {
        Optional<User> user = Optional.empty();

        if (username != null) {
            user = userRepository.findByUsername(username);
        } else if (firstName != null) {
            user = userRepository.findByUserProfile_FirstName(firstName);
        } else if (lastName != null) {
            user = userRepository.findByUserProfile_LastName(lastName);
        }

        if (user.isPresent()) {
            User foundUser = user.get();
            // Convert the found user to UserDetailsExportDTO
            UserDetailsExportDTO userDetails = getUserDetailsExportDTO(foundUser);
            return userDetails;
        } else {
            return null;
        }
    }

    @Override
    public boolean isAdminSuper(String username) {
User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        return  user.getRoles().stream()
                .anyMatch(role -> role.getName() == RoleEnum.ADMIN_SUPER);
    }

    @Override
    public boolean isOwnAccount(String username, Long userId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userId.equals(user.getId());
    }

    @Override
    public boolean isAdminUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        return  user.getRoles().stream()
                .anyMatch(role -> role.getName() == RoleEnum.ADMIN_USER);
    }

}
