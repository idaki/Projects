package bg.softuni.authenticationservice.service.password;

import bg.softuni.authenticationservice.UserPasswordRepository;

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



}
