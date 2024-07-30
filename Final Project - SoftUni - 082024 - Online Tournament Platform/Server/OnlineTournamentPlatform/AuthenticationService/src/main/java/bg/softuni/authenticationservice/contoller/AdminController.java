package bg.softuni.authenticationservice.controller;

import bg.softuni.userservice.models.dto.gson.UpdatePasswordRequest;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.PasswordService;
import bg.softuni.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final PasswordService passwordService;

    public AdminController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        passwordService.updatePasswordByAdmin(updatePasswordRequest.getUserId(), updatePasswordRequest.getNewPassword());
        return ResponseEntity.noContent().build();
    }
}
