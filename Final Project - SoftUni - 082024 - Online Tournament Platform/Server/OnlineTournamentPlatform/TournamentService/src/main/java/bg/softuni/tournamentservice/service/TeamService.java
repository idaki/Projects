package bg.softuni.tournamentservice.service;

import bg.softuni.tournamentservice.model.ExportDto.TeamExportDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamService {






    void removePlayer(Long teamId, Long userId);

    List<TeamExportDTO> getMyTeams(String token);
    List<TeamExportDTO> getTeamsByTournamentId(Long tournamentId, String jwt);
}