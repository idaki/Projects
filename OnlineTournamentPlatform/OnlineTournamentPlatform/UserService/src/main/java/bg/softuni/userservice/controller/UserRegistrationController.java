package bg.softuni.userservice.controller;


import bg.softuni.userservice.models.dto.gson.UserRegisterDTO;

import bg.softuni.userservice.service.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-consumer")
    public ResponseEntity<String> registerConsumer(@RequestBody UserRegisterDTO registerDTO) {
        try {
           userService.register(registerDTO.getUsername(), registerDTO.getPassword(),registerDTO.getEmail());
            return ResponseEntity.ok("Consumer registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consumer registration failed: " + e.getMessage());
        }
    }
}
