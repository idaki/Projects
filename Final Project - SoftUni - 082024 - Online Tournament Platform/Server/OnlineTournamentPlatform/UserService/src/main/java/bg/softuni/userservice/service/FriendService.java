package bg.softuni.userservice.service;

import bg.softuni.userservice.models.dto.FriendDTO;
import bg.softuni.userservice.models.entity.user.User;

import java.util.List;

public interface FriendService {

    List<FriendDTO> getAllFriends(String jwt);
}
