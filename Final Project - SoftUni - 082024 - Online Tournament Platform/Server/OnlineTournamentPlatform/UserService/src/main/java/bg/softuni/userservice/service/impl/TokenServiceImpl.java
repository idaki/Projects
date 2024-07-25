package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public TokenServiceImpl(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }
    @Override
    public User getUserByToken(String token){

        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("No token found");
        }
        User userNotWorking = tokenOpt.get().getUser();
        String username = tokenOpt.get().getUser().getUsername();
        Optional<User> userOpt = this.userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {throw new RuntimeException("No user found");}

        return userOpt.get();
    }
}
