package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.PasswordRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender, PasswordRepository passwordRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;

        this.passwordRepository = passwordRepository;
    }

    @Override
    public void generateResetToken(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Password password = user.getPassword();
            String token = UUID.randomUUID().toString();
            password.setResetPasswordToken(token);
            password.setResetPasswordTokenExpiryDate(LocalDateTime.now().plusHours(24));
            userRepository.save(user);

            // Send the reset token via email
            sendResetTokenEmail(user.getEmail(), token);
        }
    }

    private void sendResetTokenEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please click on the following link: http://localhost:5173/newpassword?token=" + token);
        mailSender.send(message);
    }

    @Override
    public void updatePassword(String token, String newPassword) {
        Optional<User> userOptional = userRepository.findByPasswordResetToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Password password = user.getPassword();
            password.setPasswordHash(passwordEncoder.encode(newPassword));
            password.setPasswordSetDate(LocalDateTime.now());
            password.setResetPasswordToken(null);
            password.setResetPasswordTokenExpiryDate(null);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    @Override
    public void setResetToken(String userId, String token) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Password password = user.getPassword();
            password.setResetPasswordToken(token);
            password.setResetPasswordTokenExpiryDate(LocalDateTime.now().plusHours(24));
            userRepository.save(user);
        }
    }

    @Override
    public boolean isResetTokenValid(String token) {
        Optional<User> userOptional = userRepository.findByPasswordResetToken(token);
        return userOptional.isPresent() && userOptional.get().getPassword().getResetPasswordTokenExpiryDate().isAfter(LocalDateTime.now());
    }

    @Override
    public String getUserIdByResetToken(String token) {
        Optional<User> userOptional = userRepository.findByPasswordResetToken(token);
        return userOptional.map(user -> String.valueOf(user.getId())).orElse(null);
    }

    @Override
    public Password getUserByResetToken(String token) {
        return this.passwordRepository.findUserByResetPasswordToken(token);
    }
}
