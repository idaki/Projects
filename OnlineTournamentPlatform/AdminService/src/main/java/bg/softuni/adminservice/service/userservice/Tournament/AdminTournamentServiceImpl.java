package bg.softuni.adminservice.service.userservice.Tournament;

import bg.softuni.teamservice.entity.Team;
import bg.softuni.teamservice.repository.TeamRepository;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Service
public class AdminTournamentServiceImpl implements bg.softuni.adminservice.service.userservice.tournament.AdminTournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    public AdminTournamentServiceImpl(TournamentRepository tournamentRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
    }
@Override
    public void createTournament() {
        Tournament tournament = new Tournament();
        tournament=tournament.setName("Tournament1");
   this.tournamentRepository.save(tournament);

    }

    @Override
    public void addTeamToTournament() {
        Team team = this.teamRepository.findByName("Team1");
        Tournament tournament = this.tournamentRepository.findByName("Tournament1");
        tournament.getTeams().add(team);
        tournamentRepository.save(tournament);
    }
}
