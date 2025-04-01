package bg.softuni.userservice.utils.utills.UserFinderUtil;

import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserFinderUtil {

    private final UserRepository userRepository;

    public UserFinderUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String username, String firstName, String lastName) {
        if (username != null) {
            return this.userRepository.findByUsername(username);
        } else if (firstName != null) {
            return this.userRepository.findByUserProfile_FirstName(firstName);
        } else if (lastName != null) {
            return this.userRepository.findByUserProfile_LastName(lastName);
        }
        return Optional.empty();
    }

    public boolean isExistingUser(String username, String password) {
        return findByUsername(username).isPresent() || findByEmail(username).isPresent();
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}

