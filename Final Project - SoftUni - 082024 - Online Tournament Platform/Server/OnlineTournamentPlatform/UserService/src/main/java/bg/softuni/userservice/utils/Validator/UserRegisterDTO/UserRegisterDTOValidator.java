package bg.softuni.userservice.utils.Validator.UserRegisterDTO;

import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.service.UserService;

public interface UserRegisterDTOValidator {


    void validate(UserRegisterDTO registerDTO);
}
