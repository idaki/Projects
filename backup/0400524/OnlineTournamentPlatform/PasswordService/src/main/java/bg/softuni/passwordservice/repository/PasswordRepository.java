package bg.softuni.passwordservice.repository;


import bg.softuni.userservice.models.entity.password.UserPassword;
import bg.softuni.userservice.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<UserPassword, Long> {

    Optional<UserPassword> findByUser(User user);

}
