package bg.softuni.userservice.service;

import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserCrudService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void register(UserRegisterDTO registerDTO);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    void InitUser(String username, String password, String roleInput);
}
