package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);

    // Custom query to find a user by reset password token in the Password entity
    Optional<User> findByPassword_ResetPasswordToken(String resetPasswordToken);


    @Query("SELECT u FROM User u JOIN FETCH u.password p WHERE p.resetPasswordToken = :token")
    Optional<User> findByPasswordResetToken(@Param("token") String token);
}
