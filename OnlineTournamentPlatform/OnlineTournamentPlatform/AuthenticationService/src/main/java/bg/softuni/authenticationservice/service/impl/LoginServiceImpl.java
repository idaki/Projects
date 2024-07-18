package bg.softuni.authenticationservice.service.impl;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.service.LoginService;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(LoginDTO loginDTO) {
        Optional<User> userByEmailOptional = userRepository.findByEmail(loginDTO.getUsernameOrEmail());
        Optional<User> userByUsernameOptional = userRepository.findByUsername(loginDTO.getUsernameOrEmail());

        if (userByEmailOptional.isEmpty() && userByUsernameOptional.isEmpty()) {
            return false; // No user found
        }

        User user;

        if (userByEmailOptional.isPresent()) {
            user = userByEmailOptional.get();
        } else {
            user = userByUsernameOptional.get();
        }

        if (user.getPassword() == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword().getPasswordHash())) {
            return false; // No password set or password does not match
        }

        // Authenticate with Spring Security to handle the security context
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), // or user.getEmail() depending on your login strategy
                            loginDTO.getPassword()
                    )
            );
            return authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            return false; // Authentication failed
        }
    }

    @Override
    public boolean loginAfterPasswordUpdate(String usernameOrEmail, String newPassword) {
        Optional<User> userOptional = userRepository.findByUsername(usernameOrEmail).or(() -> userRepository.findByEmail(usernameOrEmail));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), // or user.getEmail() depending on your login strategy
                                newPassword
                        )
                );
                return authentication.isAuthenticated();
            } catch (AuthenticationException e) {
                return false; // Authentication failed
            }
        }
        return false; // User not found
    }
}
