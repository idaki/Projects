package bg.softuni.userservice.service.interfaces.user;

import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.user.User;

import java.util.List;

public interface UserCrudService {

    void register(UserRegisterDTO registerDTO);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    void InitUser(String username, String password, String roleInput);
}
