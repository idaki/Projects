package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.entity.Friend;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.FriendRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllFriends(Long userId) {

      List<User> users=  friendRepository.findAllByUserId(userId).stream()
                .map(Friend::getFriend)
                .collect(Collectors.toList());
        System.out.println();
        return users;
    }
}
