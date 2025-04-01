package bg.softuni.userservice.service.interfaces.user;

import bg.softuni.userservice.models.entity.user.User;

public interface UserAuthService  {

    boolean isAdminSuper(String username);

    boolean isAdminUser(String username);

    boolean isOwnAccount(String username, Long userId);

    User findUserByToken(String jwt);
}
