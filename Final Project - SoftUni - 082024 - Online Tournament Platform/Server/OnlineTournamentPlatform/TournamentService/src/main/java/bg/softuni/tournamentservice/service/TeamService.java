package bg.softuni.tournamentservice.service;

import bg.softuni.tournamentservice.model.ExportDto.TeamExportDTO;

import java.util.List;

public interface TeamService {
    void addPlayer();
    void removePlayer();
    List<TeamExportDTO> getMyTeams(String token);
    List<TeamExportDTO> getTeamsByTournamentId(Long tournamentId, String jwt);
}