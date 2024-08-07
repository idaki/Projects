package bg.softuni.tournamentservice.service.impl;

import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
import bg.softuni.exceptionhandlerservice.ValidationException;
import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import bg.softuni.tournamentservice.model.Asset;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.dto.TournamentDTO;
import bg.softuni.tournamentservice.model.dto.TournamentSignupDTO;
import bg.softuni.tournamentservice.repository.GameRepository;
import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.service.TournamentService;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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
        if (!isValidTournamentData(tournamentCreateDTO)) {
            throw new ValidationException("Invalid tournament data");
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

    @Override
    public TournamentDTO getTournamentById(Long id, String jwt) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament != null) {
            return convertToDto(tournament);
        }
        return null;
    }

    public boolean signupForTournament(String jwt, TournamentSignupDTO signupDTO) {
        User user = userService.findUserByToken(jwt);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user token");
        }

        // Find the tournament by ID
        Tournament tournament = tournamentRepository.findById(signupDTO.getTournamentId()).orElse(null);
        if (tournament == null) {
            return false; // Tournament not found
        }

        // Create a new team
        Team team = new Team(signupDTO.getTeamName(), 5); // Assuming a default capacity of 5
        team.setTournament(tournament);
        team.getUsers().add(user); // Add the user to the team

        teamRepository.save(team);

        // Optionally add the team to the tournament's team list
        tournament.getTeams().add(team);
        tournamentRepository.save(tournament);

        return true;
    }
}
