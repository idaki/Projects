package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.dto.TeamExportDTO;
import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.tournamentservice.service.TeamService;
import bg.softuni.tournamentservice.utils.events.TeamMapper.TeamMapper;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TokenService tokenService;
    private final TeamMapper teamMapper;  //convertToTeamExportDTO


    @Autowired
    public TeamServiceImpl(UserRepository userRepository, TeamRepository teamRepository, TokenService tokenService, TeamMapper teamMapper) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.tokenService = tokenService;
        this.teamMapper = teamMapper;
    }

    @Override
    public void removePlayer(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        team.getUsers().remove(user);
        teamRepository.save(team);
    }

    @Override
    public List<TeamExportDTO> getMyTeams(String token) {
        User user = tokenService.getUserByToken(token);
        List<Team> teamList = teamRepository.getAllByUsers(user);

        return teamList.stream()
                .map(teamMapper::convertToTeamExportDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamExportDTO> getTeamsByTournamentId(Long tournamentId, String jwt) {
        List<Team> teams = teamRepository.findByTournamentId(tournamentId);

        return teams.stream()
                .map(teamMapper::convertToTeamExportDTO)
                .collect(Collectors.toList());
    }
}
