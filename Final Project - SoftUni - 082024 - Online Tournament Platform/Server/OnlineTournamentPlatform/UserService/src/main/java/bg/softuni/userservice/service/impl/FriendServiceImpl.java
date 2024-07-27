package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.FriendDTO;
import bg.softuni.userservice.models.entity.Friend;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.FriendRepository;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Optional<Token> tokenOpt = tokenRepository.findByToken(jwt);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("No token found");
        }

        User user = tokenOpt.get().getUserSecurity().getUser();
        if (user == null) {
            throw new RuntimeException("No user found for the provided token");
        }

        Set<Friend> friends = user.getFriends();

        List<FriendDTO> result = new ArrayList<>();
        for (Friend friend : friends) {
            result.add(new FriendDTO(friend.getFriend().getId()
                    , friend.getFriend().getUserProfile().getFirstName()
                    , friend.getFriend().getUserProfile().getLastName()
                    , friend.getFriend().getUserProfile().getAvatar()
            )
            );
        }

        return result;
    }
}
