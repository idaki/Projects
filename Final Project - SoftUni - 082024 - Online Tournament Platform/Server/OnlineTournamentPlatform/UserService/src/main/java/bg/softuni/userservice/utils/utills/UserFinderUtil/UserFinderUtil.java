package bg.softuni.userservice.utils.utills.UserFinderUtil;

import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;

import java.util.Optional;

public class UserFinderUtil {

    private  static UserRepository userRepository;


    public static Optional<User> findUser(String username, String firstName, String lastName) {
        if (username != null) {
            return userRepository.findByUsername(username);
        } else if (firstName != null) {
            return userRepository.findByUserProfile_FirstName(firstName);
        } else if (lastName != null) {
            return userRepository.findByUserProfile_LastName(lastName);

        }
        return Optional.empty();
    }
}
