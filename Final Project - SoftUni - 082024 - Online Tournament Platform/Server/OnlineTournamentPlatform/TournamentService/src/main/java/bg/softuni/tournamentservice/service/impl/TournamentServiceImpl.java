package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.Asset;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.repository.GameRepository;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.service.TournamentService;


import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

User user = userService.findUserByToken(jwt);

        // Map the tournamentDTO to a Tournament entity
        Tournament tournament = modelMapper.map(tournamentCreateDTO, Tournament.class);

        // Set the manager of the tournament
        tournament.setManager(user);

        // Save the tournament and return true if successful
        tournamentRepository.save(tournament);
        return true;
    }




    private TournamentDTO convertToDto(Tournament tournament) {
        TournamentDTO dto = new TournamentDTO();

        dto.setId(tournament.getId());
        dto.setName(tournament.getName());

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
}
