package bg.softuni.userservice.controller;


import bg.softuni.userservice.models.dto.gson.ConsumerRegisterDTO;
import bg.softuni.userservice.service.consumer.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ConsumerRegistrationController {

    private final ConsumerService consumerService;

    @Autowired
    public ConsumerRegistrationController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/register-consumer")
    public ResponseEntity<String> registerConsumer(@RequestBody ConsumerRegisterDTO consumerDTO) {
        try {
            consumerService.registerConsumer(consumerDTO.getUsername(), consumerDTO.getPassword(), consumerDTO.getEmail());
            return ResponseEntity.ok("Consumer registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consumer registration failed: " + e.getMessage());
        }
    }
}
