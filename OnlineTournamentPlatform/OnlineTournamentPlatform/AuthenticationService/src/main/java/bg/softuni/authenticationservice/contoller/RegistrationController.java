package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.model.DTO.UserRegisterDTO;
import bg.softuni.authenticationservice.service.LoginService;
import bg.softuni.userservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class RegistrationController {

    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public RegistrationController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping("/register-consumer")
    public ResponseEntity<String> registerConsumer(@RequestBody UserRegisterDTO registerDTO) {
        try {
            // Register the user first
            userService.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail());

            // Attempt to log in the user automatically after registration
            boolean isAuthenticated = loginService.login(new LoginDTO(registerDTO.getUsername(), registerDTO.getPassword()));

            // Check if the authentication was successful
            if (isAuthenticated) {
                return ResponseEntity.ok("Consumer registered and logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Registration successful, but login failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consumer registration failed: " + e.getMessage());
        }
    }
}
