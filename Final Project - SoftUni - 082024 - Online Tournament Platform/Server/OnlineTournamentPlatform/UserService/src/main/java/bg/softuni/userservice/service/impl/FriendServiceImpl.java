package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.FriendDTO;
import bg.softuni.userservice.models.entity.Friend;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.FriendRepository;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.FriendService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository, UserRepository userRepository, TokenRepository tokenRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }



    @Override
    public List<FriendDTO> getAllFriends(String jwt) {
        String test = jwt;
        Optional<Token> tokenOpt = tokenRepository.findByToken(jwt);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("No token found");
        }
        User userNotWorking = tokenOpt.get().getUser();
        String username = tokenOpt.get().getUser().getUsername();
        Optional<User> userOpt = this.userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {throw new RuntimeException("No user found");}

        User user = userOpt.get();
        String name = user.getFirstName();
        Set<Friend> friends = user.getFriends();


        List<FriendDTO> result = new ArrayList<>();
        for (Friend friend : friends) {
            result.add(new FriendDTO(friend.getFriend().getFirstName(), friend.getFriend().getLastName()));
        }

        System.out.println();

        return result;
    }

}
