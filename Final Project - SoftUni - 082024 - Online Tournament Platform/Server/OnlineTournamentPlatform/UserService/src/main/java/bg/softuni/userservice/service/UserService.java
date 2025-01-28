package bg.softuni.userservice.service;//package bg.softuni.userservice.service;


import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {

    boolean isExistingUser(String username, String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void register(UserRegisterDTO registerDTO);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    void InitUser(String username, String password, String roleInput);

    boolean isAdminSuper(String username);

    boolean isAdminUser(String username);

    boolean isOwnAccount(String username, Long userId);

    User findUserByToken(String jwt);

    UserDetailsDTO getUserDetails(String username);

    UserDetailsDTO findUserByDetails(String username, String firstName, String lastName);
}