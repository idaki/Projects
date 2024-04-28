package bg.softuni.adminservice.controller;

import bg.softuni.adminservice.service.authenticationservice.AdminAuthenticationService;
import bg.softuni.adminservice.service.userservice.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired; // Added import
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AdminServiceController implements CommandLineRunner {
    private final AdminUserService adminUserService; // Made the field final
    private final AdminAuthenticationService adminAuthenticationService;

    @Autowired // Added annotation
    public AdminServiceController(AdminUserService adminUserService, AdminAuthenticationService adminAuthenticationService) { // Added constructor
        this.adminUserService = adminUserService;
        this.adminAuthenticationService = adminAuthenticationService;
    }

    @Override
    public void run(String... args) throws Exception {
       adminUserService.importCompanyUsers();
       adminUserService.importEmployeeUsers();
        adminUserService.importConsumerUsers();
        adminAuthenticationService.createPasswordsForImports();
    }
}
