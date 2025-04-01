package bg.softuni.userservice.utils.Validator.UserExistence;

import bg.softuni.exceptionhandlerservice.UserAlreadyExistsException;
import bg.softuni.userservice.repository.UserRepository;

import org.springframework.stereotype.Component;
@Component
public class UserExistenceValidator {

    private final UserRepository userRepository;

    public UserExistenceValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to check if the username already exists in the system
    public void checkIfUsernameExists(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("Username " + username + " already exists.");
        }
    }

    // Method to check if the email already exists in the system
    public void checkIfEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Email " + email + " already exists.");
        }
    }
}
