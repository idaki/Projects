package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.password.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
    public Password findUserByResetPasswordToken(String token);
}
