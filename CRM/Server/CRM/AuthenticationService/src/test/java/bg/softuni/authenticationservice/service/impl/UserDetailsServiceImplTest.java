package bg.softuni.authenticationservice.service.impl;

import bg.softuni.userservice.models.entity.authorisation.Permission;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_Success() {
        // Setup
        String usernameOrEmail = "testuser";
        User mockUser = new User();
        mockUser.setUsername(usernameOrEmail);
        mockUser.setUserSecurity(mockUserSecurityWithPassword("hashedPassword"));

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(RoleEnum.ADMIN_SUPER);
        role.setPermissions(Set.of(new Permission("READ_PRIVILEGE")));
        roles.add(role);
        mockUser.setRoles(roles);

        when(userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)).thenReturn(Optional.of(mockUser));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(usernameOrEmail);

        // Assert
        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals(usernameOrEmail, userDetails.getUsername(), "Username should match");
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN_SUPER")), "User should have ROLE_USER authority");
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("READ_PRIVILEGE")), "User should have READ_PRIVILEGE authority");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Setup
        String usernameOrEmail = "nonexistentuser";
        when(userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail))
                .thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException thrown = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(usernameOrEmail),
                "Expected loadUserByUsername to throw UsernameNotFoundException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("User not found with username or email: " + usernameOrEmail));
    }

    private UserSecurity mockUserSecurityWithPassword(String passwordHash) {
        UserSecurity userSecurity = mock(UserSecurity.class);
        Password password = mock(Password.class);
        when(password.getPasswordHash()).thenReturn(passwordHash);
        when(userSecurity.getPassword()).thenReturn(password);
        return userSecurity;
    }
}
