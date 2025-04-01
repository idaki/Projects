package bg.softuni.userservice.service.interfaces.friend;

import bg.softuni.userservice.models.dto.FriendDTO;

import java.util.List;

public interface FriendService {

    List<FriendDTO> getAllFriends(String jwt);
}
