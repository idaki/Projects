package bg.softuni.tournamentservice.utils.Builders.TeamBuilder;

import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.userservice.models.entity.user.User;

public class TeamBuilderImpl implements TeamBuilder {

    private Tournament tournament;
    private String teamName;
    private User manager;

    @Override
    public TeamBuilder withTournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    @Override
    public TeamBuilder withTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    @Override
    public TeamBuilder withManager(User manager) {
        this.manager = manager;
        return this;
    }

    @Override
    public Team build() {
        if (tournament == null || teamName == null || manager == null) {
            throw new IllegalStateException("Missing required fields to build Team");
        }
        Team team = new Team(teamName, tournament.getTeamSize());
        team.setManager(manager);
        team.setTournament(tournament);
        return team;
    }
}
