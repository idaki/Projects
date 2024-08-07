package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.service.JwtService;

import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDetailsController {

    private final UserService userService;
    private final JwtService jwtService;;

@Autowired
    public UserDetailsController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/details")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@RequestHeader("Authorization") String token) {
        // Extract the JWT token from the Bearer string
        String jwt = token.substring(7).trim();

        // Extract username from JWT
        String username = jwtService.extractAllClaims(jwt).getSubject();

        UserDetailsDTO userDetails = userService.getUserDetails(username);
        return ResponseEntity.ok(userDetails);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId, @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        token = token.substring(7);  // Remove the "Bearer " prefix
        String username = jwtService.extractUsername(token);  // Extract the username from the token

        if (userService.isAdminSuper(username) ||
                (userService.isAdminUser(username) && userService.isOwnAccount(username, userId))) {

            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(403).body("Forbidden: Insufficient permissions");
        }
    }

}
