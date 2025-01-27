package bg.softuni.userservice.utils.Validator.UserExistence;

import bg.softuni.exceptionhandlerservice.UserAlreadyExistsException;
import bg.softuni.userservice.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserExistenceValidatorImpl implements UserExistenceValidator {

    private final UserRepository userRepository;

    public UserExistenceValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void checkIfUsernameExists(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("Username " + username + " already exists.");
        }
    }

    @Override
    public void checkIfEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Email " + email + " already exists.");
        }
    }
}
