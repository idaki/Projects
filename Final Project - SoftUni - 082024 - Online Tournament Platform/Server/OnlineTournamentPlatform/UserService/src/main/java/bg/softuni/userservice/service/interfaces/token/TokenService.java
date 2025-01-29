package bg.softuni.userservice.service.interfaces.token;

import bg.softuni.userservice.models.entity.user.User;

public interface TokenService {
    User getUserByToken(String token);
}
