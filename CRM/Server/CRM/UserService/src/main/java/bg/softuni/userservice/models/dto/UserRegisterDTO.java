package bg.softuni.userservice.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotNull(message = "Username is required")
    @Size(min = 1, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
        private String email;


    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
