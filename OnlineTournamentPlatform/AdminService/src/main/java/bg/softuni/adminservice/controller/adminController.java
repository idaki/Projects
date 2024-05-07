package bg.softuni.adminservice.controller;

import bg.softuni.adminservice.service.userservice.AdminUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class adminController implements CommandLineRunner {
   private final AdminUserService adminUserService;

    public adminController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.adminUserService.creatConsumer();
//        this.adminUserService.createNewCompany();
//        this.adminUserService.createNewEmployee();

//        this.adminUserService.removeConsumer();
//        this.adminUserService.removeEmployee();
//        this.adminUserService.removeCompany();
    }
}
