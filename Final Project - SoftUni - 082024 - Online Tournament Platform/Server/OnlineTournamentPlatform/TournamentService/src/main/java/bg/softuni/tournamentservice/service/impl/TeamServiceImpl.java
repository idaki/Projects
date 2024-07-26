package bg.softuni.tournamentservice.service.impl;




import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.model.viewDto.TeamDTO;
import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.tournamentservice.service.TeamService;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImpl implements TeamService {


    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TokenService tokenService;



@Autowired
    public TeamServiceImpl(UserRepository userRepository, TeamRepository teamRepository, TokenService tokenService) {

    this.userRepository = userRepository;this.teamRepository = teamRepository;
    this.tokenService = tokenService;
}


    @Override
    @Transactional
    public void addPlayer() {

    }

    @Override
    public void removePlayer() {
        // Implementation for removing a player from a team
    }

    @Override
    public List<TeamDTO> getMyTeams(String token) {
        User user = tokenService.getUserByToken(token);
        List<Team> teamList = teamRepository.getAllByUsers(user);

        List<TeamDTO> teamDTOList = new ArrayList<>();
        for (Team team : teamList) {
            TeamDTO teamDTO = new TeamDTO(); // Instantiate inside the loop
            teamDTO.setTeamName(team.getName());
            teamDTO.setId(team.getId());
            teamDTOList.add(teamDTO);
        }

        return teamDTOList;
    }
}
