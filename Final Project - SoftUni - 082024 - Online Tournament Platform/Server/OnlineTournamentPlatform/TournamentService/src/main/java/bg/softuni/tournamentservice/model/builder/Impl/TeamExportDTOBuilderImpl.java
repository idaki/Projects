package bg.softuni.tournamentservice.model.builder.Impl;

import bg.softuni.tournamentservice.model.builder.TeamExportDTOBuilder;
import bg.softuni.tournamentservice.model.dto.TeamExportDTO;


import java.util.List;

public class TeamExportDTOBuilderImpl implements TeamExportDTOBuilder {

    private  Long id;
    private  String name;
    private  List<String> members;
    private  List<Long> userIds;
    private TeamExportDTO teamExportDTO;




    @Override
    public TeamExportDTOBuilder withID(Long id) {
        this.id= id;
        return this;
    }

    @Override
    public TeamExportDTOBuilder withName(String name) {
       this.name= name;
        return this;
    }

    @Override
    public TeamExportDTOBuilder withMembers(List<String> members) {
        this.members= members;
        return this;
    }

    @Override
    public TeamExportDTOBuilder withTeams(List<Long> teams) {
        userIds= teams;
        return this;
    }

    @Override
    public TeamExportDTO build() {
        this.teamExportDTO= new TeamExportDTO(id,name,members,userIds);

    return this.teamExportDTO;
    }

    @Override
    public TeamExportDTO getTeamExportDTO() {
        return this.teamExportDTO;
    }
}
