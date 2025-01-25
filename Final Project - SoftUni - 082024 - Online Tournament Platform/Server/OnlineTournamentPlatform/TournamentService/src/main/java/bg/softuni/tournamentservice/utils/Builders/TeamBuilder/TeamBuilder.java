package bg.softuni.tournamentservice.utils.Builders.TeamBuilder;

import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.userservice.models.entity.user.User;

public interface TeamBuilder {
    TeamBuilder withTournament(Tournament tournament);
    TeamBuilder withTeamName(String teamName);
    TeamBuilder withManager(User manager);
    Team build();
}
