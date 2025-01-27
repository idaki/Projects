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


    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;

    }

    @Override
    public User getUserByToken(String token) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("No token found");
        }
        Token tokenEntity = tokenOpt.get();
        User user = tokenEntity.getUserSecurity().getUser();
        if (user == null) {
            throw new RuntimeException("No user found for the token");
        }
        return user;
    }
}
