package bg.softuni.tournamentservice.utils.TouurnamentValidator;

import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
import bg.softuni.exceptionhandlerservice.ValidationException;

import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.tournamentservice.repository.GameRepository;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TournamentValidatorImpl implements TournamentValidator {

    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameRepository gameRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentValidatorImpl(ValidationUtil validationUtil, UserService userService,
                                   GameRepository gameRepository, TournamentRepository tournamentRepository) {
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameRepository = gameRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public void validateTournamentData(TournamentCreateDTO tournamentCreateDTO) {
        if (!validationUtil.isValid(tournamentCreateDTO)) {
            Set<ConstraintViolation<TournamentCreateDTO>> violations = validationUtil.getViolations(tournamentCreateDTO);
            String errorMessage = validationUtil.getFormattedErrorMessage(violations);
            throw new ValidationException(errorMessage);
        }
    }

    @Override
    public User authenticateUser(String jwt) {
        User user = userService.findUserByToken(jwt);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user token");
        }
        return user;
    }

    @Override
    public Game validateGameExistence(String gameName) {
        return gameRepository.findByName(gameName)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }

    @Override
    public void checkForDuplicateTournament(Game game, User user, String tournamentName) {
        if (tournamentRepository.findByGameAndManagerAndName(game, user, tournamentName).isPresent()) {
            throw new DuplicateTournamentException("A tournament with these unique fields already exists.");
        }
    }
}
