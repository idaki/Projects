package bg.softuni.webservice;

import bg.softuni.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;

public class SuperAdminInit implements CommandLineRunner {
    private final UserService userService;



    public SuperAdminInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createSuperAdmin();
    }
}


