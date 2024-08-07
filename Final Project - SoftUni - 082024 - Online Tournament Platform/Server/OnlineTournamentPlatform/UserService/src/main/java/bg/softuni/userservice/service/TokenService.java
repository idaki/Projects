package bg.softuni.userservice.service;

import bg.softuni.userservice.models.entity.user.User;

public interface TokenService {
    User getUserByToken(String token);
}
