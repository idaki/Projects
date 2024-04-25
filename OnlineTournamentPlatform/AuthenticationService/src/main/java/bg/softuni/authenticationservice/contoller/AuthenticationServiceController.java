package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationServiceController implements CommandLineRunner {
private final UserService userService;
public AuthenticationServiceController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.importPasswords();
    }
}
