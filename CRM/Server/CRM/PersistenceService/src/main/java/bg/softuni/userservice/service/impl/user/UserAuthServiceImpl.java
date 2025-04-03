package bg.softuni.userservice.service.impl.user;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.interfaces.user.UserAuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserAuthServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean isAdminSuper(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getRoles().stream().anyMatch(role -> role.getName() == RoleEnum.ADMIN_SUPER);
    }

    @Override
    public boolean isOwnAccount(String username, Long userId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userId.equals(user.getId());
    }

    @Override
    public boolean isAdminUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getRoles().stream().anyMatch(role -> role.getName() == RoleEnum.ADMIN_SUPER);
    }

    @Override
    public User findUserByToken(String jwt) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(jwt);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("Token not found");
        }
        return tokenOpt.get().getUserSecurity().getUser();
    }
}
