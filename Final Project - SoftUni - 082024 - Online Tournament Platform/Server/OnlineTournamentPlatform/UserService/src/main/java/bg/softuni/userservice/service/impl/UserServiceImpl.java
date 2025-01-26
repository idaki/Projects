package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.*;
import bg.softuni.userservice.models.enums.RoleEnum;

import bg.softuni.userservice.repository.*;
import bg.softuni.userservice.service.UserService;
import bg.softuni.userservice.utils.events.UserDeleteEvent;
import bg.softuni.userservice.utils.events.Validator.UserExistence.UserExistenceValidator;
import bg.softuni.userservice.utils.events.Validator.UserRegisterDTO.UserRegisterDTOValidator;
import bg.softuni.userservice.utils.events.buiider.UserBuilder.UserBuilder;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final UserProfileRepository userProfileRepository;
    private final Validator validator;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRegisterDTOValidator userRegisterDTOValidator;
    private final UserExistenceValidator  userExistenceValidator;

    private final UserBuilder userBuilder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, TokenRepository tokenRepository, UserSecurityRepository userSecurityRepository, UserProfileRepository userProfileRepository, Validator validator, ApplicationEventPublisher eventPublisher, UserRegisterDTOValidator userRegisterDTOValidator, UserExistenceValidator userExistenceValidator, UserBuilder userBuilder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.userSecurityRepository = userSecurityRepository;
        this.userProfileRepository = userProfileRepository;
        this.validator = validator;
        this.eventPublisher = eventPublisher;
        this.userRegisterDTOValidator = userRegisterDTOValidator;
        this.userExistenceValidator = userExistenceValidator;
        this.userBuilder = userBuilder;
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
    public void register(UserRegisterDTO registerDTO) {
        userRegisterDTOValidator.validate(registerDTO , this );

         User user =   userBuilder.withUsername(registerDTO.getUsername())
                    .withEmail(registerDTO.getEmail())
                    .withProfile("","","/assets/avatars/dexter.png")
                    .withPassword(registerDTO.getPassword()).build();

        userRepository.save(user);

    }


    public UserDetailsDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username)
                .orElse(null);

        if (user == null) {
            return null; // or handle it according to your requirements
        }

        UserProfile profile = user.getUserProfile();

        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUsername(user.getUsername());
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setAvatar(profile.getAvatar());
        dto.setId(user.getId());

        return dto;
    }

    private static UserDetailsDTO getUserDetailsExportDTO(User user) {
        UserDetailsDTO userDetails = new UserDetailsDTO();
        userDetails.setUsername(user.getUsername());
        userDetails.setFirstName(user.getUserProfile().getFirstName());
        userDetails.setLastName(user.getUserProfile().getLastName());
        userDetails.setEmail(user.getEmail());
        userDetails.setAvatar(user.getUserProfile().getAvatar());
        userDetails.setId(user.getId());
        return userDetails;
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            userRepository.delete(user);
            eventPublisher.publishEvent(new UserDeleteEvent(this, user));
        }
    }

    @Override
    public void InitUser( String username,String password, String roleInput) {

        String email = username.toLowerCase() + "@serdicagrid.com";

        userExistenceValidator.checkIfUsernameExists(username);
        userExistenceValidator.checkIfEmailExists(email);

        User user = userBuilder
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .withProfile("","","/assets/avatars/dexter.png")
                .withRole(roleInput)
                .build();

        user = userRepository.save(user);

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
    public UserDetailsDTO findUserByDetails(String username, String firstName, String lastName) {
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
            UserDetailsDTO userDetails = getUserDetailsExportDTO(foundUser);
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