package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserProfile;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.models.enums.RoleEnum;
import bg.softuni.userservice.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private UserSecurityRepository userSecurityRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername_UserExists() {
        // Arrange
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findByUsername(username);

        // Assert
        assertTrue(result.isPresent(), "User should be present");
        assertEquals(username, result.get().getUsername(), "Username does not match");
    }

    @Test
    void testFindByUsername_UserNotFound() {
        // Arrange
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.findByUsername(username);

        // Assert
        assertFalse(result.isPresent(), "User should not be present");
    }

    @Test
    void testCreateUser() {
        // Arrange
        String username = "testuser";
        String email = "testuser@example.com";
        String roleName = "ADMIN_USER";

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        Role role = new Role();
        role.setName(RoleEnum.valueOf(roleName));

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName(RoleEnum.valueOf(roleName))).thenReturn(role);
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        // Act
        User result = userService.registerUser(username, "password", email);

        // Assert
        assertNotNull(result, "User should not be null");
        assertEquals(username, result.getUsername(), "Username does not match");
        assertEquals(email, result.getEmail(), "Email does not match");
        assertTrue(result.getRoles().contains(role), "Role should be assigned");
        assertEquals("hashedPassword", result.getUserSecurity().getPassword().getPasswordHash(), "Password should be hashed");

        // Verify interactions
        verify(userRepository, times(1)).save(any(User.class));
        verify(roleRepository, times(1)).findByName(RoleEnum.valueOf(roleName));
        verify(passwordEncoder, times(1)).encode("password");
    }

    @Test
    void testCreateUserProfileAndSecurity() {
        // Arrange
        String username = "testuser";
        String email = "testuser@example.com";

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUser(user);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setUserProfile(userProfile);
            savedUser.setUserSecurity(userSecurity);
            return savedUser;
        });
        when(userSecurityRepository.save(any(UserSecurity.class))).thenReturn(userSecurity);
        when(roleRepository.findByName(RoleEnum.ADMIN_USER)).thenReturn(new Role(RoleEnum.ADMIN_USER));

        // Act
        User result = userService.registerUser(username, "password", email);

        // Assert
        assertNotNull(result.getUserProfile(), "UserProfile should not be null");
        assertNotNull(result.getUserSecurity(), "UserSecurity should not be null");

        // Verify interactions
        verify(userSecurityRepository, times(1)).save(any(UserSecurity.class));
        verify(roleRepository, times(1)).findByName(RoleEnum.ADMIN_USER);
    }

    @Test
    void testGetUserDetails_UserExists() {
        // Arrange
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        user.setEmail("testuser@example.com");

        UserProfile profile = new UserProfile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setAvatar("/avatar.png");
        user.setUserProfile(profile);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        UserDetailsExportDTO dto = userService.getUserDetails(username);

        // Assert
        assertNotNull(dto, "UserDetailsExportDTO should not be null");
        assertEquals(username, dto.getUsername(), "Username does not match");
        assertEquals("John", dto.getFirstName(), "First name does not match");
        assertEquals("Doe", dto.getLastName(), "Last name does not match");
        assertEquals("/avatar.png", dto.getAvatar(), "Avatar URL does not match");
    }

    @Test
    void testGetUserDetails_UserNotFound() {
        // Arrange
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        UserDetailsExportDTO dto = userService.getUserDetails(username);

        // Assert
        assertNull(dto, "UserDetailsExportDTO should be null if user is not found");
    }

    @Test
    void testDeleteUserByUsername_UserExists() {
        // Arrange
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        // Act
        userService.deleteUserByUsername(username);

        // Assert
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUserByUsername_UserNotFound() {
        // Arrange
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        userService.deleteUserByUsername(username);

        // Assert
        verify(userRepository, never()).delete(any(User.class));
    }
    @Test
    public void testInitUserWhenUserDoesNotExist() {
        String roleInput = "ADMIN_USER";
        String username = "admin";
        String password = "password";
        String email = "admin@serdicagrid.com";

        // Mock repository responses
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(roleRepository.findByName(RoleEnum.ADMIN_USER)).thenReturn(null);
        when(roleRepository.save(any(Role.class))).thenReturn(new Role(RoleEnum.ADMIN_USER));

        // Mock save operations
        User mockUser = mock(User.class);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(userSecurityRepository.save(any(UserSecurity.class))).thenReturn(new UserSecurity());
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(new UserProfile());

        // Act
        userService.InitUser(roleInput, password, username);

        // Assert
        verify(userRepository, times(2)).save(any(User.class)); // Adjust to 2 times
        verify(userSecurityRepository).save(any(UserSecurity.class));
        verify(userProfileRepository).save(any(UserProfile.class));
    }


    @Test
    public void testFindUserByToken() {
        String token = "validToken";
        User mockUser = mock(User.class);
        Token mockToken = mock(Token.class);
        UserSecurity mockUserSecurity = mock(UserSecurity.class);

        // Configure mocks
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(mockToken));
        when(mockToken.getUserSecurity()).thenReturn(mockUserSecurity);
        when(mockUserSecurity.getUser()).thenReturn(mockUser);

        // Act
        User result = userService.findUserByToken(token);

        // Assert
        assertNotNull(result);
        assertEquals(mockUser, result);
    }


    @Test
    public void testFindUserByDetails() {
        String username = "admin";

        // Create a mock UserProfile with expected values
        UserProfile mockProfile = mock(UserProfile.class);
        when(mockProfile.getFirstName()).thenReturn("FirstName");
        when(mockProfile.getLastName()).thenReturn("LastName");

        // Create a mock User and set its UserProfile
        User mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn(username); // Mock the username getter
        when(mockUser.getUserProfile()).thenReturn(mockProfile);

        // Mock the repository to return the mock user
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // Create a UserDetailsExportDTO with expected values
        UserDetailsExportDTO expectedDTO = UserDetailsExportDTO.builder()
                .username(username)
                .firstName("FirstName")
                .lastName("LastName")
                .build();

        // Act
        UserDetailsExportDTO result = userService.findUserByDetails(username, null, null);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getUsername(), result.getUsername(), "Username should match");
        assertEquals(expectedDTO.getFirstName(), result.getFirstName(), "FirstName should match");
        assertEquals(expectedDTO.getLastName(), result.getLastName(), "LastName should match");
    }


    @Test
    public void testIsAdminSuper() {
        String username = "admin";
        User mockUser = mock(User.class);
        Role mockRole = mock(Role.class);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
        when(mockUser.getRoles()).thenReturn(Set.of(mockRole));
        when(mockRole.getName()).thenReturn(RoleEnum.ADMIN_SUPER);

        // Act
        boolean result = userService.isAdminSuper(username);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsOwnAccount() {
        String username = "admin";
        Long userId = 1L;
        User mockUser = mock(User.class);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
        when(mockUser.getId()).thenReturn(userId);

        // Act
        boolean result = userService.isOwnAccount(username, userId);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsAdminUser() {
        String username = "admin";
        User mockUser = mock(User.class);
        Role mockRole = mock(Role.class);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
        when(mockUser.getRoles()).thenReturn(Set.of(mockRole));
        when(mockRole.getName()).thenReturn(RoleEnum.ADMIN_USER);

        // Act
        boolean result = userService.isAdminUser(username);

        // Assert
        assertTrue(result);
    }
}

