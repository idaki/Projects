package bg.softuni.authenticationservice.service.impl;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.model.DTO.UpdatePasswordDTO;
import bg.softuni.authenticationservice.service.LoginService;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.PasswordService;
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
    private final PasswordService passwordService;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordService passwordService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordService = passwordService;
    }

    @Override
    public boolean login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginDTO.getUsernameOrEmail());
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByUsername(loginDTO.getUsernameOrEmail());
        }

        if (userOptional.isEmpty()) {
            return false; // No user found
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword().getPasswordHash())) {
            return false; // Password does not match
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            return authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            return false; // Authentication failed
        }
    }

    @Override
    public boolean loginAfterPasswordUpdate(UpdatePasswordDTO updatePasswordDTO) {
        Password password = passwordService.getUserByResetToken(updatePasswordDTO.getToken());
        User user = password.getUser();
        LoginDTO loginDTO = new LoginDTO(user.getUsername(), updatePasswordDTO.getNewPassword());
        return this.login(loginDTO);
    }
}
