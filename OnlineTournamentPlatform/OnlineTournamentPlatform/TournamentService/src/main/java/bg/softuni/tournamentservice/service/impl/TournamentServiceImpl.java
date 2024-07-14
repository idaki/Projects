package bg.softuni.tournamentservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.gameservice.model.Asset;
import bg.softuni.gameservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl extends CrudServiceImpl<Tournament, Long> implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        super(tournamentRepository);
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
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
