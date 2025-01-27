package bg.softuni.userservice.service;

import bg.softuni.userservice.models.dto.UserDetailsDTO;

public interface UserInfoService {
    UserDetailsDTO getUserDetails(String username);

    UserDetailsDTO findUserByDetails(String username, String firstName, String lastName);
}
