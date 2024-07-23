package bg.softuni.userservice.models.entity.password;

import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String passwordHash;

    @Column
    private LocalDateTime passwordSetDate;

    @Column
    private String resetPasswordToken;

    @Column
    private Boolean isActiveResetPasswordToken;

    @Column
    private LocalDateTime resetPasswordTokenExpiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Password() {
    }

    public Password(String passwordHash, LocalDateTime passwordSetDate, User user) {
        this.passwordHash = passwordHash;
        this.passwordSetDate = passwordSetDate;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getPasswordSetDate() {
        return passwordSetDate;
    }

    public void setPasswordSetDate(LocalDateTime passwordSetDate) {
        this.passwordSetDate = passwordSetDate;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public LocalDateTime getResetPasswordTokenExpiryDate() {
        return resetPasswordTokenExpiryDate;
    }

    public void setResetPasswordTokenExpiryDate(LocalDateTime resetPasswordTokenExpiryDate) {
        this.resetPasswordTokenExpiryDate = resetPasswordTokenExpiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActiveResetPasswordToken() {
        return isActiveResetPasswordToken;
    }

    public void setActiveResetPasswordToken(Boolean activeResetPasswordToken) {
        this.isActiveResetPasswordToken = activeResetPasswordToken;
    }
}
