package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.service.LoginService;
import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.models.dto.UserRegisterDTO;
import bg.softuni.userservice.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> registerConsumer(@RequestBody UserRegisterDTO registerDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            // Register the user first
            userService.register(registerDTO);

            // Attempt to log in the user automatically after registration
            boolean isAuthenticated = loginService.login(new LoginDTO(registerDTO.getUsername(), registerDTO.getPassword()));

            // Check if the authentication was successful
            if (isAuthenticated) {
                response.put("message", "Consumer registered and logged in successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Registration successful, but login failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (ValidationException e) {
            response.put("message", "Validation failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", "User already exists: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
