package bg.softuni.authenticationservice.controller;

import bg.softuni.authenticationservice.model.DTO.ResetPasswordDTO;
import bg.softuni.authenticationservice.model.DTO.UpdatePasswordDTO;
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

@RestController
@RequestMapping("/api")
public class ResetPasswordController {

    private final PasswordService passwordService;
    private final LoginService loginService;

    @Autowired
    public ResetPasswordController(PasswordService passwordService, LoginService loginService) {
        this.passwordService = passwordService;
        this.loginService = loginService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        passwordService.generateResetToken(resetPasswordDTO.getEmail());
        return ResponseEntity.ok("Password reset token has been sent to your email.");
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        try {
            Password password = passwordService.getUserByResetToken(updatePasswordDTO.getToken());
            User user = password.getUser();
            passwordService.updatePassword(updatePasswordDTO.getToken(), updatePasswordDTO.getNewPassword());

            // Automatically log in the user after password update
            if (loginService.loginAfterPasswordUpdate(user.getUsername(), updatePasswordDTO.getNewPassword())) {
                return ResponseEntity.ok("Password successfully updated and user logged in.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to log in after password update.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
