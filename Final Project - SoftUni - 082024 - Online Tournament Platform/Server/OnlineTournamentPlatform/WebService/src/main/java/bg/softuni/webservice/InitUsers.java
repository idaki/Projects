package bg.softuni.webservice;

import bg.softuni.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;

public class InitUsers implements CommandLineRunner {

    private final String ADMIN_SUPER_USERNAME= "admin";
    private final String ADMIN_SUPER_PASSWORD= "admin";
    private final String ADMIN_SUPER_ROLE= "ADMIN_SUPER";
    private final String ADMIN_USER_USERNAME= "user";
    private final String ADMIN_USER_PASSWORD= "user";
    private final String ADMIN_USER_ROLE= "ADMIN_USER";

    private final UserService userService;



    public InitUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.InitUser(ADMIN_SUPER_ROLE,ADMIN_SUPER_PASSWORD,ADMIN_SUPER_USERNAME);
        userService.InitUser(ADMIN_USER_ROLE,ADMIN_USER_PASSWORD,ADMIN_USER_USERNAME);
    }
}


