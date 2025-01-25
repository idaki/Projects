package bg.softuni.tournamentservice.utils.Builders.TournamentBuilder;

import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.userservice.models.entity.user.User;

import java.time.LocalDate;
import java.util.Date;

public interface TournamentBuilder {
    TournamentBuilder withName(String name);
    TournamentBuilder withCategory(String category);
    TournamentBuilder withSummary(String summary);
    TournamentBuilder withStartDate(Date startDate);
    TournamentBuilder withEndDate(Date endDate);
    TournamentBuilder withNumberOfTeams(int numberOfTeams);
    TournamentBuilder withTeamSize(int teamSize);
    TournamentBuilder withManager(User manager);
    TournamentBuilder withGame(Game game);
    Tournament build();
}
