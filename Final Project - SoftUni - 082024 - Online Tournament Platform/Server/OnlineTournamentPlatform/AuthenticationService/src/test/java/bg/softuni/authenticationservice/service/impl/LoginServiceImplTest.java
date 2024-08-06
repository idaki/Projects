package bg.softuni.authenticationservice.service.impl;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.model.DTO.UpdatePasswordDTO;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private LoginServiceImpl loginService;

    private User user;

    @BeforeEach
    public void setUp() {
        // Create a mock User and Password
        user = new User();
        UserSecurity userSecurity = new UserSecurity();
        Password password = new Password();
        password.setPasswordHash("encodedPassword");
        userSecurity.setPassword(password);
        user.setUserSecurity(userSecurity);

        // Set up necessary stubs
        when(userRepository.findByUsernameOrEmail(eq("testUser"), any(String.class)))
                .thenReturn(Optional.of(user));

        // Only stub passwordEncoder.matches if you have tests that actually use it
        when(passwordEncoder.matches(eq("password"), eq("encodedPassword")))
                .thenReturn(true);
    }


    @Test
    public void testLogin_Success() {
        LoginDTO loginDTO = new LoginDTO("testUser", "password");
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        boolean result = loginService.login(loginDTO);

        assertThat(result).isTrue();
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }



    @Test
    public void testLogin_AuthenticationException() {
        LoginDTO loginDTO = new LoginDTO("testUser", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Authentication failed") {});

        boolean result = loginService.login(loginDTO);

        assertThat(result).isFalse();
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}
