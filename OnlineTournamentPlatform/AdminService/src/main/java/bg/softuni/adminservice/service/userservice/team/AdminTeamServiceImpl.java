package bg.softuni.adminservice.service.userservice.team;

import bg.softuni.teamservice.entity.Team;
import bg.softuni.teamservice.repository.TeamRepository;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminTeamServiceImpl implements AdminTeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public AdminTeamServiceImpl(TeamRepository teamRepository, UserService userService, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void createTeam() {
        Team team1 = new Team()
                .setName("Team1");
        Team team2 = new Team()
                .setName("Team2");
        User user1 = this.userRepository.findByUsername("User1").get();

        User user2 = this.userRepository.findByUsername("User2").get();
        User user3 = this.userRepository.findByUsername("User3").get();



        team1.getUsers().add(user1);
        team1.getUsers().add(user2);
        team2.getUsers().add(user3);

        this.teamRepository.save(team1);
        this.teamRepository.save(team2);

    }

}
