package bg.softuni.adminservice.controller;


import bg.softuni.adminservice.service.userservice.team.AdminTeamService;
import bg.softuni.adminservice.service.userservice.tournament.AdminTournamentService;
import bg.softuni.adminservice.service.userservice.user.AdminUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class adminController implements CommandLineRunner {
   private final AdminUserService adminUserService;
   public final AdminTournamentService adminTournamentService;
   private final AdminTeamService adminTeamService;

    public adminController(AdminUserService adminUserService, AdminTournamentService adminTournamentService, AdminTeamService adminTeamService) {
        this.adminUserService = adminUserService;
        this.adminTournamentService = adminTournamentService;
        this.adminTeamService = adminTeamService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.adminUserService.creatConsumer();
        this.adminUserService.createNewCompany();
        this.adminUserService.createNewEmployee();
//
//        this.adminUserService.removeConsumer();
//        this.adminUserService.removeEmployee();
//        this.adminUserService.removeCompany();
        this.adminTournamentService.createTournament();
        this.adminTeamService.createTeam();
    }
}
