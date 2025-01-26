package bg.softuni.userservice.utils.events.Validator.UserRegisterDTO;

import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.service.UserService;

public interface UserRegisterDTOValidator {


    void validate(UserRegisterDTO registerDTO, UserService userService);
}
