package bg.softuni.authenticationservice.service;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.model.DTO.UserRegisterDTO;

public interface LoginService {
    boolean login(LoginDTO loginDTO);


    boolean loginAfterPasswordUpdate(String username, String newPassword);
}
