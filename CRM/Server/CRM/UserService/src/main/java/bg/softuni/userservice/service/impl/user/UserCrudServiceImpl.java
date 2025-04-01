package bg.softuni.userservice.service.impl.user;

import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.interfaces.user.UserCrudService;
import bg.softuni.userservice.utils.Validator.UserExistence.UserExistenceValidator;
import bg.softuni.userservice.utils.Validator.UserRegisterDTO.UserRegisterDTOValidator;
import bg.softuni.userservice.utils.buiider.UserBuilder.UserBuilder;
import bg.softuni.userservice.utils.events.UserDeleteEvent;
import bg.softuni.userservice.utils.utills.UserFinderUtil.UserFinderUtil;
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
    private    UserBuilder userBuilder;
    private final UserFinderUtil userFinderUtil;
   private final  UserExistenceValidator userExistenceValidator;

    public UserCrudServiceImpl(UserRepository userRepository, ApplicationEventPublisher eventPublisher, UserRegisterDTOValidator userRegisterDTOValidator, UserBuilder userBuilder, UserFinderUtil userFinderUtil, UserExistenceValidator userExistenceValidator) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userRegisterDTOValidator = userRegisterDTOValidator;
        this.userBuilder = userBuilder;
        this.userFinderUtil = userFinderUtil;
        this.userExistenceValidator = userExistenceValidator;
    }


    @Override
    public void register(UserRegisterDTO registerDTO) {
        userRegisterDTOValidator.validate(registerDTO);
        if (userFinderUtil.isExistingUser(registerDTO.getUsername(), registerDTO.getPassword())) {
            throw new RuntimeException("User already exists!");
        }

        User user = userBuilder.withUsername(registerDTO.getUsername())
                .withEmail(registerDTO.getEmail())
                .withProfile("","","/assets/avatars/dexter.png")
                .withRole(String.valueOf(RoleEnum.ADMIN_ADMIN))
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

       userExistenceValidator.checkIfEmailExists(email);userExistenceValidator.checkIfUsernameExists(username);


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
