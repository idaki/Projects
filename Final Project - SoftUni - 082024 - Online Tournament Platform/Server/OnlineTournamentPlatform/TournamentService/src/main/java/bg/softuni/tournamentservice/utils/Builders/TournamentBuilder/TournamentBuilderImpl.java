package bg.softuni.tournamentservice.utils.Builders.TournamentBuilder;

import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.userservice.models.entity.user.User;

import java.time.LocalDate;
import java.util.Date;

public class TournamentBuilderImpl implements TournamentBuilder {

    private String name;
    private String category;
    private String summary;
    private Date startDate;
    private Date endDate;
    private int numberOfTeams;
    private int teamSize;
    private User manager;
    private Game game;

    @Override
    public TournamentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TournamentBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public TournamentBuilder withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @Override
    public TournamentBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public TournamentBuilder withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    @Override
    public TournamentBuilder withNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
        return this;
    }

    @Override
    public TournamentBuilder withTeamSize(int teamSize) {
        this.teamSize = teamSize;
        return this;
    }

    @Override
    public TournamentBuilder withManager(User manager) {
        this.manager = manager;
        return this;
    }

    @Override
    public TournamentBuilder withGame(Game game) {
        this.game = game;
        return this;
    }

    @Override
    public Tournament build() {
        if (name == null || manager == null || game == null || startDate == null || endDate == null) {
            throw new IllegalStateException("Missing required fields for building Tournament");
        }

        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setCategory(category);
        tournament.setSummary(summary);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        tournament.setNumberOfTeams(numberOfTeams);
        tournament.setTeamSize(teamSize);
        tournament.setManager(manager);
        tournament.setGame(game);
        return tournament;
    }
}
