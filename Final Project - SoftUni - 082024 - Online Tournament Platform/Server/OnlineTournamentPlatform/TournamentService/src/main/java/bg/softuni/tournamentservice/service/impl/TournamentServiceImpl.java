package bg.softuni.tournamentservice.service.impl;

import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
import bg.softuni.exceptionhandlerservice.ValidationException;
import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.dto.*;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.tournamentservice.service.TournamentService;

import bg.softuni.tournamentservice.utils.Factory.TournamentDTO.TournamentDTOConverterFactory;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserService userService;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final ValidationUtil validationUtil;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, UserService userService,
                                 GameRepository gameRepository, ModelMapper modelMapper,
                                 TeamRepository teamRepository, ValidationUtil validationUtil) {
        this.tournamentRepository = tournamentRepository;
        this.userService = userService;
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        return tournaments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getSubscribedInTournaments(String jwt) {
        User user = userService.findUserByToken(jwt);
        // Implementation placeholder
        return List.of(); // Return empty list as placeholder
    }

    @Override
    public List<TournamentDTO> getManagedTournaments(String jwt) {
        User user = userService.findUserByToken(jwt);
        List<Tournament> tournaments = tournamentRepository.findByManagerId(user.getId());
        return tournaments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getWatchlistTournaments(String jwt) {
        User user = userService.findUserByToken(jwt);
        List<Tournament> tournaments = tournamentRepository.findByFollowerId(user.getId());
        return tournaments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean isValidTournamentData(TournamentCreateDTO tournamentCreateDTO) {
        return validationUtil.isValid(tournamentCreateDTO);
    }

    public boolean createTournament(String jwt, TournamentCreateDTO tournamentCreateDTO) {
        validateTournamentData(tournamentCreateDTO);
        User user = authenticateUser(jwt);
        Game game = validateGameExistence(tournamentCreateDTO.getGame());
        checkForDuplicateTournament(game, user, tournamentCreateDTO.getName());
        Tournament tournament = createAndSaveTournament(tournamentCreateDTO, user, game);
        return true;
    }

    private void validateTournamentData(TournamentCreateDTO tournamentCreateDTO) {
        Set<ConstraintViolation<TournamentCreateDTO>> violations = validationUtil.getViolations(tournamentCreateDTO);
        if (!violations.isEmpty()) {
            String errorMessage = validationUtil.getFormattedErrorMessage(violations);
            throw new ValidationException(errorMessage);
        }
    }

    private User authenticateUser(String jwt) {
        User user = userService.findUserByToken(jwt);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user token");
        }
        return user;
    }

    private Game validateGameExistence(String gameName) {
        return gameRepository.findByName(gameName)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }

    private void checkForDuplicateTournament(Game game, User user, String tournamentName) {
        Optional<Tournament> existingTournament = tournamentRepository.findByGameAndManagerAndName(game, user, tournamentName);
        if (existingTournament.isPresent()) {
            throw new DuplicateTournamentException("A tournament with these unique fields already exists.");
        }
    }

    private Tournament createAndSaveTournament(TournamentCreateDTO tournamentCreateDTO, User user, Game game) {
        Tournament tournament = modelMapper.map(tournamentCreateDTO, Tournament.class);
        tournament.setManager(user);
        tournament.setGame(game);
        tournamentRepository.save(tournament);
        return tournament;
    }

    private TournamentDTO convertToDto(Tournament tournament) {
        TournamentDTOConverterFactory factory = new TournamentDTOConverterFactory(modelMapper);
        return factory.convert(tournament);
    }

    public TournamentDTO getTournamentById(Long id, String jwt) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return modelMapper.map(tournament, TournamentDTO.class);
    }

    public boolean signupForTournament(String jwt, TournamentSignupDTO signupDTO) {
        User user = userService.findUserByToken(jwt);
        Tournament tournament = tournamentRepository.findById(signupDTO.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        Team team = createAndSaveTeam(signupDTO, user, tournament);
        return true;
    }

    private Team createAndSaveTeam(TournamentSignupDTO signupDTO, User user, Tournament tournament) {
        Team team = new Team(signupDTO.getTeamName(), 10); // Assume capacity is 10
        team.setManager(user);
        team.setTournament(tournament);
        teamRepository.save(team);
        tournament.getTeams().add(team);
        tournamentRepository.save(tournament);
        return team;
    }
}
