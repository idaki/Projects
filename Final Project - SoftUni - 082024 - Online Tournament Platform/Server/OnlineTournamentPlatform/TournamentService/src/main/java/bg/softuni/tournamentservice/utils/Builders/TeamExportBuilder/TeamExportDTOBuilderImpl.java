package bg.softuni.tournamentservice.utils.Builders.TeamExportBuilder;

import bg.softuni.tournamentservice.model.dto.TeamExportDTO;
import java.util.List;

public class TeamExportDTOBuilderImpl implements TeamExportDTOBuilder {

    private Long id;
    private String name;
    private List<String> members;
    private List<Long> teamIds;

    @Override
    public TeamExportDTOBuilder withID(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public TeamExportDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TeamExportDTOBuilder withMembers(List<String> members) {
        this.members = members;
        return this;
    }

    @Override
    public TeamExportDTOBuilder withTeams(List<Long> teamIds) {
        this.teamIds = teamIds;
        return this;
    }

    @Override
    public TeamExportDTO build() {
        return new TeamExportDTO(id, name, members, teamIds);
    }
}
