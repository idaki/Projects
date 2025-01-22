package bg.softuni.tournamentservice.utils.Mappers.TeamMapper;

import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.model.dto.TeamExportDTO;

public interface TeamMapper {
    TeamExportDTO convertToTeamExportDTO(Team team);
}
