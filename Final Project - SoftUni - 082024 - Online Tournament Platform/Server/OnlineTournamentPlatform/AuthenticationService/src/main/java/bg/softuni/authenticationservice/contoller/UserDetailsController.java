package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDetailsController {

    private final UserService userService;
    private final JwtService jwtService;

@Autowired
    public UserDetailsController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/details")
    public ResponseEntity<UserDetailsExportDTO> getUserDetails(@RequestHeader("Authorization") String token) {
        // Extract the JWT token from the Bearer string
        String jwt = token.substring(7).trim();

        // Extract username from JWT
        String username = jwtService.extractAllClaims(jwt).getSubject();

        UserDetailsExportDTO userDetails = userService.getUserDetails(username);
        return ResponseEntity.ok(userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        try {

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String username = jwtService.extractUsername(token);

                userService.deleteUserByUsername(username);
                return ResponseEntity.ok().body("User deleted successfully.");
            } else {
                return ResponseEntity.badRequest().body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
        }
    }

}
