import bg.softuni.userservice.models.dto.FriendDTO;
import bg.softuni.userservice.models.entity.Friend;
import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.user.UserProfile;
import bg.softuni.userservice.models.entity.user.UserSecurity;
import bg.softuni.userservice.repository.FriendRepository;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.impl.FriendServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FriendServiceImplTest {

    @InjectMocks
    private FriendServiceImpl friendService;

    @Mock
    private FriendRepository friendRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFriends_TokenNotFound() {
        // Arrange
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> friendService.getAllFriends("invalidToken"));
        assertEquals("No token found", thrown.getMessage());
    }

    @Test
    void testGetAllFriends_UserNotFound() {
        // Arrange
        Token token = mock(Token.class);
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.of(token));
        UserSecurity userSecurity = mock(UserSecurity.class);
        when(token.getUserSecurity()).thenReturn(userSecurity);
        when(userSecurity.getUser()).thenReturn(null); // Simulate no user found

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            friendService.getAllFriends("invalidToken");
        });

        assertEquals("No user found for the provided token", thrown.getMessage());
    }

    @Test
    void testGetAllFriends_UserHasNoFriends() {
        // Arrange
        User user = new User();
        user.setFriends(new HashSet<>());
        user.setId(1L);  // Ensure ID is set
        Token token = mock(Token.class);
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.of(token));
        UserSecurity userSecurity = mock(UserSecurity.class);
        when(token.getUserSecurity()).thenReturn(userSecurity);
        when(userSecurity.getUser()).thenReturn(user);

        // Act
        List<FriendDTO> result = friendService.getAllFriends("validToken");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Result should be empty when the user has no friends");
    }


    @Test
    void testGetAllFriends_UserHasFriends() {
        // Arrange
        User user = new User();
        user.setId(1L);  // Ensure ID is set

        UserProfile userProfile1 = new UserProfile();
        userProfile1.setFirstName("John");
        userProfile1.setLastName("Doe");
        userProfile1.setAvatar("/avatar1.png");

        UserProfile userProfile2 = new UserProfile();
        userProfile2.setFirstName("Jane");
        userProfile2.setLastName("Doe");
        userProfile2.setAvatar("/avatar2.png");

        User friendUser1 = new User();
        friendUser1.setUserProfile(userProfile1);
        friendUser1.setId(2L);  // Ensure friend IDs are set

        User friendUser2 = new User();
        friendUser2.setUserProfile(userProfile2);
        friendUser2.setId(3L);  // Ensure friend IDs are set

        Friend friend1 = new Friend();
        friend1.setFriend(friendUser1);

        Friend friend2 = new Friend();
        friend2.setFriend(friendUser2);

        Set<Friend> friends = new HashSet<>();
        friends.add(friend1);
        friends.add(friend2);

        user.setFriends(friends);

        Token token = mock(Token.class);
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.of(token));
        UserSecurity userSecurity = mock(UserSecurity.class);
        when(token.getUserSecurity()).thenReturn(userSecurity);
        when(userSecurity.getUser()).thenReturn(user);

        // Act
        List<FriendDTO> result = friendService.getAllFriends("validToken");

        // Debug output
        System.out.println("Number of friends in result: " + result.size());
        for (FriendDTO dto : result) {
            System.out.println("FriendDTO ID: " + dto.getId());
            System.out.println("FriendDTO First Name: " + dto.getFirstName());
            System.out.println("FriendDTO Last Name: " + dto.getLastName());
            System.out.println("FriendDTO Avatar: " + dto.getAvatar());
        }

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "The number of friends returned should be 2");

        // Validate the friends in the result
        List<Long> friendIds = Arrays.asList(2L, 3L);
        for (FriendDTO dto : result) {
            assertTrue(friendIds.contains(dto.getId()), "Unexpected friend ID in result");
        }
    }

}
