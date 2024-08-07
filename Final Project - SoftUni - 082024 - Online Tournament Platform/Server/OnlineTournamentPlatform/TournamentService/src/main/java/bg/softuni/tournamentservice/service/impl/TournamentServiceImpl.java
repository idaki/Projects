package bg.softuni.tournamentservice.service.impl;

import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
import bg.softuni.exceptionhandlerservice.ValidationException;
import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.dto.*;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.tournamentservice.service.TournamentService;
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
        userService.findUserByToken(jwt);
        return List.of();
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
        Set<ConstraintViolation<TournamentCreateDTO>> violations = validationUtil.getViolations(tournamentCreateDTO);
        if (!violations.isEmpty()) {
            String errorMessage = validationUtil.getFormattedErrorMessage(violations);
            throw new ValidationException(errorMessage);
        }

        // Validate JWT and find the user
        User user = userService.findUserByToken(jwt);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user token");
        }

        // Validate game existence
        Game game = gameRepository.findByName(tournamentCreateDTO.getGame())
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        // Check for existing tournament with the same unique fields
        Optional<Tournament> existingTournament = tournamentRepository.findByGameAndManagerAndName(
                game, user, tournamentCreateDTO.getName()
        );
        if (existingTournament.isPresent()) {
            throw new DuplicateTournamentException("A tournament with these unique fields already exists.");
        }

        // Map DTO to entity
        Tournament tournament = modelMapper.map(tournamentCreateDTO, Tournament.class);

        // Set manager and game
        tournament.setManager(user);
        tournament.setGame(game);

        // Save the tournament
        tournamentRepository.save(tournament);

        return true;
    }

    private TournamentDTO convertToDto(Tournament tournament) {
        TournamentDTO dto = modelMapper.map(tournament, TournamentDTO.class);

        Game game = tournament.getGame();
        if (game != null) {
            String description = "PLACEHOLDER TEXT"; // Replace this with actual logic if necessary
            Set<Asset> assets = game.getAssets();
            String img = "";
            if (assets != null && !assets.isEmpty()) {
                img = assets.iterator().next().getUrl();
            }
            dto.setDescription(description);
            dto.setUrl(img);
        } else {
            dto.setDescription("No game associated");
            dto.setUrl(""); // Default or placeholder image URL if no game is associated
        }

        return dto;
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

        Team team = new Team(signupDTO.getTeamName(), 10); // Assume capacity is 10 for this example
        team.setManager(user);
        team.setTournament(tournament);

        teamRepository.save(team);
        tournament.getTeams().add(team);

        tournamentRepository.save(tournament);
        return true;
    }
}
