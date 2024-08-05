package bg.softuni.initservice;


import bg.softuni.initservice.contoller.InitController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    private static final String ADMIN_SUPER_USERNAME = "admin";
    private static final String ADMIN_SUPER_PASSWORD = "admin";
    private static final String ADMIN_SUPER_ROLE = "ADMIN_SUPER";
    private static final String ADMIN_USER_USERNAME = "user";
    private static final String ADMIN_USER_PASSWORD = "user";
    private static final String ADMIN_USER_ROLE = "ADMIN_USER";
    private static final String DATABASE_SCRIPT_NAME = "DataInput.txt";

    private final InitController initController;


    public Init(InitController initController) {
        this.initController = initController;

    }

    @Override
    public void run(String... args) throws Exception {
        initController.initUser(ADMIN_SUPER_ROLE, ADMIN_SUPER_USERNAME, ADMIN_SUPER_PASSWORD);
        initController.initUser(ADMIN_USER_ROLE, ADMIN_USER_USERNAME, ADMIN_USER_PASSWORD);

        initController.executeSqlScript(DATABASE_SCRIPT_NAME);


    }
}
