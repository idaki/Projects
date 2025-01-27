package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.*;
import bg.softuni.userservice.models.enums.RoleEnum;

import bg.softuni.userservice.repository.*;
import bg.softuni.userservice.service.UserService;
import bg.softuni.userservice.utils.events.UserDeleteEvent;
import bg.softuni.userservice.utils.Validator.UserExistence.UserExistenceValidator;
import bg.softuni.userservice.utils.Validator.UserRegisterDTO.UserRegisterDTOValidator;
import bg.softuni.userservice.utils.buiider.UserBuilder.UserBuilder;

import bg.softuni.userservice.utils.buiider.UserDetailsDTOBuilder.UserDetailsDTOBuilder;
import bg.softuni.userservice.utils.utills.UserFinderUtil.UserFinderUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRegisterDTOValidator userRegisterDTOValidator;
    private final UserExistenceValidator  userExistenceValidator;
    private final UserBuilder userBuilder;
    private final UserDetailsDTOBuilder userDetailsDTOBuilder;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, ApplicationEventPublisher eventPublisher, UserRegisterDTOValidator userRegisterDTOValidator, UserExistenceValidator userExistenceValidator, UserBuilder userBuilder, UserDetailsDTOBuilder userDetailsDTOBuilder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.eventPublisher = eventPublisher;
        this.userRegisterDTOValidator = userRegisterDTOValidator;
        this.userExistenceValidator = userExistenceValidator;
        this.userBuilder = userBuilder;
        this.userDetailsDTOBuilder = userDetailsDTOBuilder;
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


    @Override
    public UserDetailsDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = user.getUserProfile();

        return userDetailsDTOBuilder
                .withUsername(user.getUsername())
                .withFirstName(profile.getFirstName())
                .withLastName(profile.getLastName())
                .withEmail(user.getEmail())
                .withAvatar(profile.getAvatar())
                .withId(user.getId())
                .build();
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
        Optional<User> userOpt = UserFinderUtil.findUser(username, firstName, lastName);

        return userOpt.map(user ->
                userDetailsDTOBuilder
                        .withUsername(user.getUsername())
                        .withFirstName(user.getUserProfile().getFirstName())
                        .withLastName(user.getUserProfile().getLastName())
                        .withEmail(user.getEmail())
                        .withAvatar(user.getUserProfile().getAvatar())
                        .withId(user.getId())
                        .build()
        ).orElse(null);
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