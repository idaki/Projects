package bg.softuni.teamservice.controller;


import bg.softuni.teamservice.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class TeamController implements CommandLineRunner {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.teamService.addPlayer();


    }
}
