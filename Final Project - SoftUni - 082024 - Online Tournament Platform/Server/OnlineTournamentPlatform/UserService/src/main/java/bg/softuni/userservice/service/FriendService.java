package bg.softuni.userservice.service;

import bg.softuni.userservice.models.entity.user.User;

import java.util.List;

public interface FriendService {
    List<User> getAllFriends(Long userId);
}
