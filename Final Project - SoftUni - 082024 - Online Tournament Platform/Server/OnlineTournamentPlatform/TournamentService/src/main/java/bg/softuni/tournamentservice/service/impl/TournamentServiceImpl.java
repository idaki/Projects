package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.Asset;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.service.TeamService;
import bg.softuni.tournamentservice.service.TournamentService;


import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TeamService teamService;
    private final UserService userService;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TeamService teamService, UserService userService) {
        this.tournamentRepository = tournamentRepository;
        this.teamService = teamService;
        this.userService = userService;
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

    private TournamentDTO convertToDto(Tournament tournament) {
        TournamentDTO dto = new TournamentDTO();
        Game game = tournament.getGame();
        String description = "PLACEHOLDER TEXT";
        Set<Asset> assets = game.getAssets();
        String img = "";
        if (!assets.isEmpty()) {
            img = assets.iterator().next().getUrl();
        }
        dto.setId(tournament.getId());
        dto.setName(tournament.getName());
        dto.setDescription(description);
        dto.setUrl(img);
        return dto;
    }
}
