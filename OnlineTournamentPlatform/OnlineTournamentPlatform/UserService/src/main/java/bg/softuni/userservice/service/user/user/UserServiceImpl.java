package bg.softuni.userservice.service.user.user;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(JpaRepository<User, Long> repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        super(repository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public boolean doesUserExist(String usernameOrEmail) {
        Optional<User> optionalUser = this.userRepository.findByEmail(usernameOrEmail);

        if (optionalUser.isPresent()) {
            return true;
        }

        optionalUser = this.userRepository.findByUsername(usernameOrEmail);

        return optionalUser.isPresent();
    }






    public User registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        return save(user); // Use the save method from CrudServiceImpl
    }
}
