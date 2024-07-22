package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.userservice.models.dto.gson.UserDetailsExportDTO;
import bg.softuni.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/details")
    public ResponseEntity<UserDetailsExportDTO> getUserDetails(@RequestHeader("Authorization") String token) {
        // Extract the JWT token from the Bearer string

        String jwt = token.substring(7).trim();

        // Extract username from JWT
        String username = jwtService.extractAllClaims(jwt).getSubject();

        UserDetailsExportDTO userDetails = userService.getUserDetails(username);
        return ResponseEntity.ok(userDetails);
    }

}
