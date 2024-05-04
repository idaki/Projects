package bg.softuni.adminservice.service.userservice.Tournament;

import bg.softuni.gameservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.userservice.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminTournamentServiceImpl implements AdminTournamentService {
    private final TournamentRepository tournamentRepository;

    public AdminTournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
@Override
    public void createTournament() {
        Tournament tournament = new Tournament();
        tournament=tournament.setName("Tournament1");
   this.tournamentRepository.save(tournament);

    }
}
