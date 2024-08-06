package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.repository.PasswordRepository;
import bg.softuni.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PasswordServiceImplTest {

    @InjectMocks
    private PasswordServiceImpl passwordService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private PasswordRepository passwordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateResetToken_UserFound() {
        // Arrange
        String email = "testuser@example.com";
        User user = new User();
        UserSecurity userSecurity = new UserSecurity();
        Password password = new Password();

        // Initialize the UserSecurity and Password objects
        userSecurity.setPassword(password);
        user.setUserSecurity(userSecurity);

        // Generate a token and mock the repository responses
        String token = UUID.randomUUID().toString();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordRepository.save(any(Password.class))).thenReturn(password);

        // Act
        passwordService.generateResetToken(email);

        // Assert
        assertNotNull(password.getResetPasswordToken(), "Reset token should not be null");
        assertTrue(password.getResetPasswordTokenExpiryDate().isAfter(LocalDateTime.now()), "Reset token expiry date should be in the future");
        verify(mailSender).send(any(SimpleMailMessage.class));
    }


    @Test
    void testGenerateResetToken_UserNotFound() {
        // Arrange
        String email = "testuser@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        passwordService.generateResetToken(email);

        // Assert
        // No exception expected, just verify that no email is sent
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }

    @Test
    void testUpdatePassword_TokenFound() {
        // Arrange
        String token = "valid-token";
        String newPassword = "newPassword";
        Password password = new Password();
        password.setResetPasswordToken(token);
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.of(password));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedPassword");

        // Act
        passwordService.updatePassword(token, newPassword);

        // Assert
        assertEquals("encodedPassword", password.getPasswordHash());
        assertNull(password.getResetPasswordToken());
        assertNull(password.getResetPasswordTokenExpiryDate());
        assertFalse(password.getIsActiveResetPasswordToken());
        verify(passwordRepository).save(password);
    }

    @Test
    void testUpdatePassword_TokenNotFound() {
        // Arrange
        String token = "invalid-token";
        String newPassword = "newPassword";
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordService.updatePassword(token, newPassword);
        });
        assertEquals("Invalid token", thrown.getMessage());
    }

    @Test
    void testSetResetToken_UserFound() {
        // Arrange
        String userId = "1";  // Example user ID
        String token = UUID.randomUUID().toString();

        // Create a User with UserSecurity and Password initialized
        User user = new User();
        user.setId(Long.parseLong(userId));

        UserSecurity userSecurity = new UserSecurity();
        Password password = new Password();
        password.setUserSecurity(userSecurity);  // Ensure UserSecurity is set
        userSecurity.setPassword(password);  // Link Password to UserSecurity

        // Set up the User object to return in the repository mock
        user.setUserSecurity(userSecurity);

        when(userRepository.findById(Long.parseLong(userId))).thenReturn(Optional.of(user));

        // Act
        passwordService.setResetToken(userId, token);

        // Assert
        // Verify interactions or assert the expected behavior
        verify(passwordRepository, times(1)).save(any(Password.class));
    }


    @Test
    void testSetResetToken_UserNotFound() {
        // Arrange
        String userId = "1";
        String token = UUID.randomUUID().toString();
        when(userRepository.findById(Long.parseLong(userId))).thenReturn(Optional.empty());

        // Act
        passwordService.setResetToken(userId, token);

        // Assert
        // No exception expected
        verify(passwordRepository, never()).save(any(Password.class));
    }

    @Test
    void testIsResetTokenValid_TokenValid() {
        // Arrange
        String token = "valid-token";
        Password password = new Password();
        password.setResetPasswordTokenExpiryDate(LocalDateTime.now().plusHours(1));
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.of(password));

        // Act
        boolean result = passwordService.isResetTokenValid(token);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsResetTokenValid_TokenExpired() {
        // Arrange
        String token = "expired-token";
        Password password = new Password();
        password.setResetPasswordTokenExpiryDate(LocalDateTime.now().minusHours(1));
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.of(password));

        // Act
        boolean result = passwordService.isResetTokenValid(token);

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetUserIdByResetToken_TokenFound() {
        // Arrange
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setId(1L);  // Set an example user ID

        UserSecurity userSecurity = new UserSecurity();
        Password password = new Password();
        password.setUserSecurity(userSecurity);  // Ensure UserSecurity is set
        userSecurity.setUser(user);  // Link User to UserSecurity

        password.setResetPasswordToken(token);

        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.of(password));

        // Act
        String userId = passwordService.getUserIdByResetToken(token);

        // Assert
        assertNotNull(userId, "User ID should not be null");
        assertEquals("1", userId, "User ID does not match");
    }


    @Test
    void testGetUserIdByResetToken_TokenNotFound() {
        // Arrange
        String token = "invalid-token";
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.empty());

        // Act
        String userId = passwordService.getUserIdByResetToken(token);

        // Assert
        assertNull(userId);
    }

    @Test
    void testGetUserByResetToken_TokenFound() {
        // Arrange
        String token = "valid-token";
        Password password = new Password();
        password.setResetPasswordToken(token);
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.of(password));

        // Act
        Password result = passwordService.getUserByResetToken(token);

        // Assert
        assertEquals(password, result);
    }

    @Test
    void testGetUserByResetToken_TokenNotFound() {
        // Arrange
        String token = "invalid-token";
        when(passwordRepository.findUserByResetPasswordToken(token)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordService.getUserByResetToken(token);
        });
        assertEquals("Invalid token", thrown.getMessage());
    }
}
