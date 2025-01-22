package bg.softuni.tournamentservice.utils.Builders.TeamExportBuilder;



import bg.softuni.tournamentservice.model.dto.TeamExportDTO;

import java.util.List;

public interface TeamExportDTOBuilder {
    TeamExportDTOBuilder withID(Long id);
    TeamExportDTOBuilder withName(String name);
    TeamExportDTOBuilder withMembers(List<String> members);
    TeamExportDTOBuilder withTeams(List<Long> teams);
    TeamExportDTO build();
    TeamExportDTO getTeamExportDTO();


}
