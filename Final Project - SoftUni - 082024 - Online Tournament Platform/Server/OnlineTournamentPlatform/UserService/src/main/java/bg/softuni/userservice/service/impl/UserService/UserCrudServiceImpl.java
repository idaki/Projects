package bg.softuni.userservice.service.impl.UserService;

import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.UserCrudService;
import bg.softuni.userservice.service.UserAuthService;
import bg.softuni.userservice.utils.Validator.UserExistence.UserExistenceValidator;
import bg.softuni.userservice.utils.Validator.UserRegisterDTO.UserRegisterDTOValidator;
import bg.softuni.userservice.utils.buiider.UserBuilder.UserBuilder;
import bg.softuni.userservice.utils.events.UserDeleteEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRegisterDTOValidator userRegisterDTOValidator;
    private final UserExistenceValidator userExistenceValidator;
    private final UserBuilder userBuilder;
    private final UserAuthService userAuthService;  // Inject UserAuthService instead of UserService


    public UserCrudServiceImpl(UserRepository userRepository, ApplicationEventPublisher eventPublisher,
                               UserRegisterDTOValidator userRegisterDTOValidator, UserExistenceValidator userExistenceValidator,
                               UserBuilder userBuilder, UserAuthService userAuthService) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userRegisterDTOValidator = userRegisterDTOValidator;
        this.userExistenceValidator = userExistenceValidator;
        this.userBuilder = userBuilder;
        this.userAuthService = userAuthService;  // Use UserAuthService instead
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
    public void register(UserRegisterDTO registerDTO) {
        userRegisterDTOValidator.validate(registerDTO);
        if (userAuthService.isExistingUser(registerDTO.getUsername(), registerDTO.getPassword())) {
            throw new RuntimeException("User already exists!");
        }

        User user = userBuilder.withUsername(registerDTO.getUsername())
                .withEmail(registerDTO.getEmail())
                .withProfile("","","/assets/avatars/dexter.png")
                .withPassword(registerDTO.getPassword()).build();

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
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
    public List<User> findAllUsers() {
        return new ArrayList<>(this.userRepository.findAll());
    }

    @Override
    public void InitUser(String username, String password, String roleInput) {
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

        userRepository.save(user);
    }
}
