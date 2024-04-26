package bg.softuni.authenticationservice.service.password;

import bg.softuni.authenticationservice.UserPasswordRepository;
import bg.softuni.userservice.models.entity.base.User;
import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.password.UserPassword;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordServiceImpl implements UserPasswordService {

    private final UserPasswordRepository userPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public UserPasswordServiceImpl(UserPasswordRepository userPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userPasswordRepository = userPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public <T extends User> void savePassword(T user, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        UserPassword password = new UserPassword();


        password.setHashedPassword(hashedPassword);

        if (user instanceof Consumer) {
            password.setConsumer((Consumer) user);
        } else if (user instanceof Employee) {
            password.setEmployee((Employee) user);
        } else if (user instanceof Company) {
            password.setCompany((Company) user);
        }

        userPasswordRepository.saveAndFlush(password);
    }

}
