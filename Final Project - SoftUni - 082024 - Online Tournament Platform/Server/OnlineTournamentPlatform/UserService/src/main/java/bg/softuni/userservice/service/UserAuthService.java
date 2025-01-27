package bg.softuni.userservice.service;

import bg.softuni.userservice.models.entity.user.User;

import java.util.Optional;

public interface UserAuthService {
    boolean isExistingUser(String username, String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean isAdminSuper(String username);

    boolean isAdminUser(String username);

    boolean isOwnAccount(String username, Long userId);

    User findUserByToken(String jwt);
}
