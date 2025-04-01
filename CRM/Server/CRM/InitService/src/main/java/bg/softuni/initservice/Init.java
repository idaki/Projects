package bg.softuni.initservice;


import bg.softuni.initservice.contoller.InitController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    private static final String ADMIN_SUPER_USERNAME = "super";
    private static final String ADMIN_SUPER_PASSWORD = "super";
    private static final String ADMIN_SUPER_ROLE = "ADMIN_SUPER";
    private static final String ADMIN_ADMIN_USERNAME = "admin";
    private static final String ADMIN_ADMIN_PASSWORD = "admin";
    private static final String ADMIN_ADMIN_ROLE = "ADMIN_ADMIN";
    private static final String ADMIN_INSTRUCTOR_USERNAME = "user";
    private static final String ADMIN_INSTRUCTOR_PASSWORD = "user";
    private static final String ADMIN_INSTRUCTOR_ROLE = "ADMIN_INSTRUCTOR";



    private final InitController initController;


    public Init(InitController initController) {
        this.initController = initController;

    }

    @Override
    public void run(String... args)  {
       initController.initUser( ADMIN_SUPER_USERNAME, ADMIN_SUPER_PASSWORD,ADMIN_SUPER_ROLE);
        initController.initUser( ADMIN_ADMIN_USERNAME, ADMIN_ADMIN_PASSWORD, ADMIN_ADMIN_ROLE);
        initController.initUser( ADMIN_INSTRUCTOR_USERNAME, ADMIN_INSTRUCTOR_PASSWORD, ADMIN_INSTRUCTOR_ROLE);
//
//        initController.executeSqlScript(DATABASE_SCRIPT_NAME);


    }
}
