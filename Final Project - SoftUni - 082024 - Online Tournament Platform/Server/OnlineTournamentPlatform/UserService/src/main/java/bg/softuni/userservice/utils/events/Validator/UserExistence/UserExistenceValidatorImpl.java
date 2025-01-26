package bg.softuni.userservice.utils.events.Validator.UserExistence;

import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.utils.events.Validator.UserExistence.UserExistenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistenceValidatorImpl implements UserExistenceValidator {

    private final UserRepository userRepository;

    @Autowired
    public UserExistenceValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void checkIfUsernameExists(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username " + username + " already exists.");
        }
    }

    @Override
    public void checkIfEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email " + email + " already exists.");
        }
    }
}
