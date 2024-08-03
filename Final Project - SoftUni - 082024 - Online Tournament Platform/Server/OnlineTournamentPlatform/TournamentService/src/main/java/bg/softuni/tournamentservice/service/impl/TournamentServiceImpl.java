package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.Asset;

import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.repository.GameRepository;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.service.DuplicateTournamentException;
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

    public TournamentServiceImpl(TournamentRepository tournamentRepository, UserService userService, GameRepository gameRepository, ModelMapper modelMapper) {
        this.tournamentRepository = tournamentRepository;


        this.userService = userService;
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public boolean createTournament(String jwt, TournamentCreateDTO tournamentCreateDTO) {

            // Find the user by token
            User user = userService.findUserByToken(jwt);
            if (user == null) {
                throw new IllegalArgumentException("Invalid user token");
            }

            // Find the game by name
            Game game = gameRepository.findByName(tournamentCreateDTO.getGame())
                    .orElseThrow(() -> new IllegalArgumentException("Game not found"));

            // Check if a tournament with the same unique fields already exists
            Optional<Tournament> existingTournament = tournamentRepository.findByGameAndManagerAndName(
                    game, user, tournamentCreateDTO.getName()
            );

            if (existingTournament.isPresent()) {
                throw new DuplicateTournamentException("A tournament with these unique fields already exists.");
            }

            // Map the tournamentDTO to a Tournament entity
            Tournament tournament = modelMapper.map(tournamentCreateDTO, Tournament.class);

            // Set the manager and game of the tournament
            tournament.setManager(user);
            tournament.setGame(game);

            // Save the tournament
            tournamentRepository.save(tournament);

            return true;
        }




    private TournamentDTO convertToDto(Tournament tournament) {
        TournamentDTO dto = new TournamentDTO();

        dto.setId(tournament.getId());
        dto.setName(tournament.getGame().getName());

        // Check if the game is null before accessing its properties
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

}
