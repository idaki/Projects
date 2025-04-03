package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.impl.token.TokenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByToken_Success() {
        // Arrange
        String tokenString = "valid-token";
        User user = new User();
        Token token = new Token();
        token.setToken(tokenString);
        token.setUserSecurity(new UserSecurity());
        token.getUserSecurity().setUser(user);

        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));

        // Act
        User result = tokenService.getUserByToken(tokenString);

        // Assert
        assertNotNull(result, "User should not be null");
        assertEquals(user, result, "The returned user does not match the expected user");
    }

    @Test
    void testGetUserByToken_TokenNotFound() {
        // Arrange
        String tokenString = "invalid-token";
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            tokenService.getUserByToken(tokenString);
        });
        assertEquals("No token found", thrown.getMessage());
    }

    @Test
    void testGetUserByToken_UserNotFound() {
        // Arrange
        String tokenString = "valid-token";
        Token token = new Token();
        token.setToken(tokenString);
        token.setUserSecurity(new UserSecurity());
        // User is not set

        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            tokenService.getUserByToken(tokenString);
        });
        assertEquals("No user found for the token", thrown.getMessage());
    }
}
