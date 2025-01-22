package bg.softuni.tournamentservice.utils.Mappers.TeamMapper;


import bg.softuni.tournamentservice.utils.Builders.TeamExportBuilder.TeamExportDTOBuilderImpl;
import bg.softuni.tournamentservice.utils.Builders.TeamExportBuilder.TeamExportDTOBuilder;
import bg.softuni.tournamentservice.model.dto.TeamExportDTO;
import bg.softuni.tournamentservice.model.Team;
import bg.softuni.userservice.models.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public TeamExportDTO convertToTeamExportDTO(Team team) {
        List<String> members = team.getUsers().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        List<Long> userIds = team.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());

        TeamExportDTOBuilder builder = new TeamExportDTOBuilderImpl();

        return builder.withID(team.getId())
                .withName(team.getName())
                .withMembers(members)
                .withTeams(userIds)
                .build();
    }
}
