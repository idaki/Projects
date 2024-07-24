package bg.softuni.userservice.service;

import bg.softuni.userservice.models.dto.gson.FriendRequestDTO;
import bg.softuni.userservice.models.enums.FriendRequestStatus;

public interface FriendRequestService {

        FriendRequestDTO sendFriendRequest(Long senderId, Long receiverId);
        FriendRequestDTO respondToFriendRequest(Long requestId, FriendRequestStatus status);

}
