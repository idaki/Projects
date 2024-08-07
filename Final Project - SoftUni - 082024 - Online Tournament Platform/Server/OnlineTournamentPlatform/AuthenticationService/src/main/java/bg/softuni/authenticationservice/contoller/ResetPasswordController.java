package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.model.DTO.ResetPasswordDTO;
import bg.softuni.authenticationservice.model.DTO.UpdatePasswordDTO;
import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.authenticationservice.service.LoginService;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class ResetPasswordController {

    private final PasswordService passwordService;
    private final JwtService jwtService;

    @Autowired
    public ResetPasswordController(PasswordService passwordService
            , JwtService jwtService) {
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        passwordService.generateResetToken(resetPasswordDTO.getEmail());
        return ResponseEntity.ok("Please check your email address.");
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        try {
            passwordService.updatePassword(updatePasswordDTO.getToken(), updatePasswordDTO.getNewPassword());
            // Retrieve the user based on the token
            User user = passwordService.getUserByResetToken(updatePasswordDTO.getToken()).getUserSecurity().getUser();
            // Generate a new JWT token
            String jwt = jwtService.generateToken(user.getUsername());
            // Return the new JWT token in the response
            return ResponseEntity.ok(Collections.singletonMap("accessToken", jwt));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "An unexpected error occurred: " + e.getMessage()));
        }
    }
}
