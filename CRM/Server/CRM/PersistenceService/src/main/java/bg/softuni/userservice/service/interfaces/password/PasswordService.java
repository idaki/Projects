package bg.softuni.userservice.service.interfaces.password;

import bg.softuni.userservice.models.entity.password.Password;

public interface PasswordService {
     void generateResetToken(String email) ;

    void updatePassword(String userId, String newPassword);
    void setResetToken(String userId, String token);
    boolean isResetTokenValid(String token);
    String getUserIdByResetToken(String token);

    Password getUserByResetToken(String token);
}
