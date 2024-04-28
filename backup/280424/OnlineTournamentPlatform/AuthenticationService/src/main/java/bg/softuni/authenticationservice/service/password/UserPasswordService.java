package bg.softuni.authenticationservice.service.password;

import bg.softuni.userservice.models.entity.base.User;

public interface UserPasswordService {


    <T extends User> void savePassword(T user, String rawPassword);
}
