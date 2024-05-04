package bg.softuni.adminservice.controller;

import bg.softuni.adminservice.service.userservice.Tournament.AdminTournamentService;
import bg.softuni.adminservice.service.userservice.User.AdminUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class adminController implements CommandLineRunner {
   private final AdminUserService adminUserService;
   public final AdminTournamentService adminTournamentService;

    public adminController(AdminUserService adminUserService, AdminTournamentService adminTournamentService) {
        this.adminUserService = adminUserService;
        this.adminTournamentService = adminTournamentService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.adminUserService.creatConsumer();
//        this.adminUserService.createNewCompany();
//        this.adminUserService.createNewEmployee();
//
//        this.adminUserService.removeConsumer();
//        this.adminUserService.removeEmployee();
//        this.adminUserService.removeCompany();
        this.adminTournamentService.createTournament();
    }
}
