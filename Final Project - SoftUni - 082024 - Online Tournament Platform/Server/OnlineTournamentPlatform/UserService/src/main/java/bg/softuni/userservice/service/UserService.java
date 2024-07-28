package bg.softuni.userservice.service;


;
import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.models.entity.user.User;

import java.util.List;
import java.util.Optional;


public interface UserService  {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean isExistingUser(String username, String password);

    void register(String username, String password, String email);


    UserDetailsExportDTO getUserDetails(String username);

    void deleteUserByUsername(String username);




    void InitUser(String roleInput, String password, String name);

    User findUserByToken(String jwt);

    List<User> findAllUsers();

    void deleteUserById(Long id);
}
