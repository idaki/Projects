package bg.softuni.authenticationservice.service;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.model.DTO.UpdatePasswordDTO;

public interface LoginService {
    boolean login(LoginDTO loginDTO);



    boolean loginAfterPasswordUpdate(UpdatePasswordDTO updatePasswordDTO);


}
