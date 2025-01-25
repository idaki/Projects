package bg.softuni.tournamentservice.utils.TouurnamentValidator;

import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.userservice.models.entity.user.User;

public interface TournamentValidator {
    void validateTournamentData(TournamentCreateDTO tournamentCreateDTO);

    User authenticateUser(String jwt);

    Game validateGameExistence(String gameName);

    void checkForDuplicateTournament(Game game, User user, String tournamentName);
}
