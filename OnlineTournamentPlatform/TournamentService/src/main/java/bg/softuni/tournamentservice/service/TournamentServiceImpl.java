package bg.softuni.tournamentservice.service;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl extends CrudServiceImpl<Tournament, Long> implements TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(JpaRepository<Tournament, Long> repository, TournamentRepository tournamentRepository) {
        super(repository);
        this.tournamentRepository = tournamentRepository;
    }
}
