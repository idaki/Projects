package bg.softuni.tournamentservice.service.impl;

import bg.softuni.tournamentservice.model.ExportDto.TeamExportDTO;
import bg.softuni.tournamentservice.model.Team;

import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.tournamentservice.service.TeamService;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TokenService tokenService;

    @Autowired
    public TeamServiceImpl(UserRepository userRepository, TeamRepository teamRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public void addPlayer() {
        // Implementation for adding a player to a team
    }

    @Override
    public void removePlayer() {
        // Implementation for removing a player from a team
    }

    @Override
    public List<TeamExportDTO> getMyTeams(String token) {
        User user = tokenService.getUserByToken(token);
        List<Team> teamList = teamRepository.getAllByUsers(user);

        return teamList.stream()
                .map(this::convertToTeamExportDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamExportDTO> getTeamsByTournamentId(Long tournamentId, String jwt) {
        List<Team> teams = teamRepository.findByTournamentId(tournamentId);

        return teams.stream()
                .map(this::convertToTeamExportDTO)
                .collect(Collectors.toList());
    }

    private TeamExportDTO convertToTeamExportDTO(Team team) {
        List<String> members = team.getUsers().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        List<Long> userIds = team.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());


        return TeamExportDTO.builder()
                .id(team.getId())
                .teamName(team.getName())
                .members(members)
                .userIds(userIds)
                .build();
    }
}
