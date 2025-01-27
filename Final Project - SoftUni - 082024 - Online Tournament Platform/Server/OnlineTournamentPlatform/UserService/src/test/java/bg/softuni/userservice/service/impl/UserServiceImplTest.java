//package bg.softuni.userservice.service.impl;
//
//import bg.softuni.userservice.models.dto.UserDetailsDTO;
//import bg.softuni.userservice.models.entity.Token;
//import bg.softuni.userservice.models.entity.authorisation.Role;
//import bg.softuni.userservice.models.entity.user.User;
//import bg.softuni.userservice.models.entity.user.UserProfile;
//import bg.softuni.userservice.models.entity.user.UserSecurity;
//import bg.softuni.userservice.models.enums.RoleEnum;
//import bg.softuni.userservice.repository.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.lang.reflect.Method;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class UserServiceImplTest {
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Mock
//    private RoleRepository roleRepository;
//
//    @Mock
//    private ApplicationEventPublisher eventPublisher;
//
//    @Mock
//    private TokenRepository tokenRepository;
//
//    @Mock
//    private UserSecurityRepository userSecurityRepository;
//
//    @Mock
//    private UserProfileRepository userProfileRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testFindByUsername_UserExists() {
//        // Arrange
//        String username = "testuser";
//        User user = new User();
//        user.setUsername(username);
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
//
//        // Act
//        Optional<User> result = userService.findByUsername(username);
//
//        // Assert
//        assertTrue(result.isPresent(), "User should be present");
//        assertEquals(username, result.get().getUsername(), "Username does not match");
//    }
//
//    @Test
//    void testFindByUsername_UserNotFound() {
//        // Arrange
//        String username = "testuser";
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<User> result = userService.findByUsername(username);
//
//        // Assert
//        assertFalse(result.isPresent(), "User should not be present");
//    }
//
//
//
//    @Test
//    void testCreateUserProfileAndSecurity() {
//        // Arrange
//        String username = "testuser";
//        String email = "testuser@example.com";
//
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(email);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setUser(user);
//
//        UserSecurity userSecurity = new UserSecurity();
//        userSecurity.setUser(user);
//
//        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
//            User savedUser = invocation.getArgument(0);
//            savedUser.setUserProfile(userProfile);
//            savedUser.setUserSecurity(userSecurity);
//            return savedUser;
//        });
//        when(userSecurityRepository.save(any(UserSecurity.class))).thenReturn(userSecurity);
//        when(roleRepository.findByName(RoleEnum.ADMIN_USER)).thenReturn(new Role(RoleEnum.ADMIN_USER));
//
//        // Act
//        User result = userService.registerUser(username, "password", email);
//
//        // Assert
//        assertNotNull(result.getUserProfile(), "UserProfile should not be null");
//        assertNotNull(result.getUserSecurity(), "UserSecurity should not be null");
//
//        // Verify interactions
//        verify(userSecurityRepository, times(1)).save(any(UserSecurity.class));
//        verify(roleRepository, times(1)).findByName(RoleEnum.ADMIN_USER);
//    }
//    @Test
//    public void testInitUserWhenUserDoesNotExist() {
//        String roleInput = "ADMIN_USER";
//        String username = "admin";
//        String password = "password";
//        String email = "admin@serdicagrid.com";
//
//        // Mock repository responses
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//        when(roleRepository.findByName(RoleEnum.ADMIN_USER)).thenReturn(null);
//        when(roleRepository.save(any(Role.class))).thenReturn(new Role(RoleEnum.ADMIN_USER));
//
//        // Mock save operations
//        User mockUser = mock(User.class);
//        when(userRepository.save(any(User.class))).thenReturn(mockUser);
//        when(userSecurityRepository.save(any(UserSecurity.class))).thenReturn(new UserSecurity());
//        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(new UserProfile());
//
//        // Act
//        userService.InitUser(roleInput, password, username);
//
//        // Assert
//        // Further assertions here
//    }
//    @Test
//    void testGetUserDetails_UserExists() {
//        // Arrange
//        String username = "testuser";
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail("testuser@example.com");
//
//        UserProfile profile = new UserProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setAvatar("/avatar.png");
//        user.setUserProfile(profile);
//
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
//
//        // Act
//        UserDetailsDTO dto = userService.getUserDetails(username);
//
//        // Assert
//        assertNotNull(dto, "UserDetailsExportDTO should not be null");
//        assertEquals(username, dto.getUsername(), "Username does not match");
//        assertEquals("John", dto.getFirstName(), "First name does not match");
//        assertEquals("Doe", dto.getLastName(), "Last name does not match");
//        assertEquals("/avatar.png", dto.getAvatar(), "Avatar URL does not match");
//    }
//
//    @Test
//    void testGetUserDetails_UserNotFound() {
//        // Arrange
//        String username = "testuser";
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//
//        // Act
//        UserDetailsDTO dto = userService.getUserDetails(username);
//
//        // Assert
//        assertNull(dto, "UserDetailsExportDTO should be null if user is not found");
//    }
//
//    @Test
//    void testDeleteUserByUsername_UserExists() {
//        // Arrange
//        String username = "testuser";
//        User user = new User();
//        user.setUsername(username);
//
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
//        doNothing().when(userRepository).delete(user);
//
//        // Act
//        userService.deleteUserByUsername(username);
//
//        // Assert
//        verify(userRepository, times(1)).delete(user);
//    }
//
//    @Test
//    void testDeleteUserByUsername_UserNotFound() {
//        // Arrange
//        String username = "testuser";
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//
//        // Act
//        userService.deleteUserByUsername(username);
//
//        // Assert
//        verify(userRepository, never()).delete(any(User.class));
//    }
//
//
//    @Test
//    public void testFindUserByToken() {
//        String token = "validToken";
//        User mockUser = mock(User.class);
//        Token mockToken = mock(Token.class);
//        UserSecurity mockUserSecurity = mock(UserSecurity.class);
//
//        // Configure mocks
//        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(mockToken));
//        when(mockToken.getUserSecurity()).thenReturn(mockUserSecurity);
//        when(mockUserSecurity.getUser()).thenReturn(mockUser);
//
//        // Act
//        User result = userService.findUserByToken(token);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(mockUser, result);
//    }
//
//
//    @Test
//    public void testFindUserByDetails() {
//        String username = "admin";
//
//        // Create a mock UserProfile with expected values
//        UserProfile mockProfile = mock(UserProfile.class);
//        when(mockProfile.getFirstName()).thenReturn("FirstName");
//        when(mockProfile.getLastName()).thenReturn("LastName");
//
//        // Create a mock User and set its UserProfile
//        User mockUser = mock(User.class);
//        when(mockUser.getUsername()).thenReturn(username); // Mock the username getter
//        when(mockUser.getUserProfile()).thenReturn(mockProfile);
//
//        // Mock the repository to return the mock user
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//
//        // Create a UserDetailsExportDTO with expected values
//        UserDetailsDTO expectedDTO = UserDetailsDTO.builder()
//                .username(username)
//                .firstName("FirstName")
//                .lastName("LastName")
//                .build();
//
//        // Act
//        UserDetailsDTO result = userService.findUserByDetails(username, null, null);
//
//        // Assert
//        assertNotNull(result, "Result should not be null");
//        assertEquals(expectedDTO.getUsername(), result.getUsername(), "Username should match");
//        assertEquals(expectedDTO.getFirstName(), result.getFirstName(), "FirstName should match");
//        assertEquals(expectedDTO.getLastName(), result.getLastName(), "LastName should match");
//    }
//
//
//    @Test
//    public void testIsAdminSuper() {
//        String username = "admin";
//        User mockUser = mock(User.class);
//        Role mockRole = mock(Role.class);
//
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//        when(mockUser.getRoles()).thenReturn(Set.of(mockRole));
//        when(mockRole.getName()).thenReturn(RoleEnum.ADMIN_SUPER);
//
//        // Act
//        boolean result = userService.isAdminSuper(username);
//
//        // Assert
//        assertTrue(result);
//    }
//
//    @Test
//    public void testIsOwnAccount() {
//        String username = "admin";
//        Long userId = 1L;
//        User mockUser = mock(User.class);
//
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//        when(mockUser.getId()).thenReturn(userId);
//
//        // Act
//        boolean result = userService.isOwnAccount(username, userId);
//
//        // Assert
//        assertTrue(result);
//    }
//
//    @Test
//    public void testIsAdminUser() {
//        String username = "admin";
//        User mockUser = mock(User.class);
//        Role mockRole = mock(Role.class);
//
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//        when(mockUser.getRoles()).thenReturn(Set.of(mockRole));
//        when(mockRole.getName()).thenReturn(RoleEnum.ADMIN_USER);
//
//        // Act
//        boolean result = userService.isAdminUser(username);
//
//        // Assert
//        assertTrue(result);
//    }
//    @Test
//    public void testCreateUserProfile() throws Exception {
//        // Create a mock User object
//        User user = new User(); // Assuming User has a default constructor or use a mock
//
//        // Use reflection to access the private static method
//        Method method = UserServiceImpl.class.getDeclaredMethod("createUserProfile", User.class);
//        method.setAccessible(true); // Allow access to private method
//
//        // Invoke the private method
//        UserProfile result = (UserProfile) method.invoke(null, user); // null for static method
//
//        // Assertions
//        assertEquals("", result.getFirstName(), "First name should be empty");
//        assertEquals("", result.getLastName(), "Last name should be empty");
//        assertEquals(user, result.getUser(), "User should be set correctly");
//    }
//    @Test
//    public void testFindByEmail() {
//        String email = "test@example.com";
//        User user = new User(); // Create a User instance if needed
//        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//
//        Optional<User> result = userService.findByEmail(email);
//
//        assertEquals(Optional.of(user), result, "findByEmail should return the expected user");
//    }
//
//    @Test
//    public void testIsExistingUserWhenUserExists() {
//        String username = "existingUser";
//        String email = "test@example.com";
//
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));
//        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        boolean result = userService.isExistingUser(username, email);
//
//        assertTrue(result, "isExistingUser should return true if the user exists by username");
//    }
//
//    @Test
//    public void testIsExistingUserWhenEmailExists() {
//        String username = "newUser";
//        String email = "existing@example.com";
//
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));
//
//        boolean result = userService.isExistingUser(username, email);
//
//        assertTrue(result, "isExistingUser should return true if the email exists");
//    }
//
//    @Test
//    public void testIsExistingUserWhenNeitherExists() {
//        String username = "newUser";
//        String email = "new@example.com";
//
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        boolean result = userService.isExistingUser(username, email);
//
//        assertFalse(result, "isExistingUser should return false if neither the user nor the email exists");
//    }
//}
//
