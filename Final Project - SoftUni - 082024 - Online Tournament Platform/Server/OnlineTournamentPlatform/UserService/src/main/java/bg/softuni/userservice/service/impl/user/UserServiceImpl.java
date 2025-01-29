package bg.softuni.userservice.service.impl.user;

import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.interfaces.user.UserAuthService;
import bg.softuni.userservice.service.interfaces.user.UserCrudService;
import bg.softuni.userservice.service.interfaces.user.UserInfoService;
import bg.softuni.userservice.service.interfaces.user.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserServiceImpl implements UserService {

    private final UserCrudService userCrudService;
    private final UserInfoService userDetailsService;
    private final UserAuthService userAuthService;

    // Constructor injection
    public UserServiceImpl(UserCrudService userCrudService, UserInfoService userDetailsService, UserAuthService userAuthService) {
        this.userCrudService = userCrudService;
        this.userDetailsService = userDetailsService;
        this.userAuthService = userAuthService;
    }



    @Override
    public void register(UserRegisterDTO registerDTO) {
        userCrudService.register(registerDTO);
    }

    @Override
    public void deleteUserById(Long id) {
        userCrudService.deleteUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userCrudService.findAllUsers();
    }

    @Override
    public void InitUser(String username, String password, String roleInput) {
        userCrudService.InitUser(username, password, roleInput);
    }

    @Override
    public boolean isAdminSuper(String username) {

        return userAuthService.isAdminSuper(username);
    }

    @Override
    public boolean isAdminUser(String username) {
        return userAuthService.isAdminUser(username);
    }

    @Override
    public boolean isOwnAccount(String username, Long userId) {
        return userAuthService.isOwnAccount(username, userId);
    }

    @Override
    public User findUserByToken(String jwt) {
        return userAuthService.findUserByToken(jwt);
    }

    @Override
    public UserDetailsDTO getUserDetails(String username) {
        return userDetailsService.getUserDetails(username);
    }

    @Override
    public UserDetailsDTO findUserByDetails(String username, String firstName, String lastName) {
        return userDetailsService.findUserByDetails(username, firstName, lastName);
    }
}
