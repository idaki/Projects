package bg.softuni.authenticationservice.service;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;

public interface LoginService {
    boolean login(LoginDTO loginDTO);
}
