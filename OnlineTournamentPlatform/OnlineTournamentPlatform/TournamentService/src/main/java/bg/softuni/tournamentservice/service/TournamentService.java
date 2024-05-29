package bg.softuni.tournamentservice.service;

import bg.softuni.crudservice.crud.CrudService;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;

import java.util.List;

public interface TournamentService extends CrudService<Tournament, Long> {
    List<TournamentDTO> getAllTournaments();
}
