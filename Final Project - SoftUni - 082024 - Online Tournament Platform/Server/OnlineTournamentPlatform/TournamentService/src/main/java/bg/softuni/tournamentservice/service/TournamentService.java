package bg.softuni.tournamentservice.service;


import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;

import java.util.List;

public interface TournamentService  {
    List<TournamentDTO> getAllTournaments();

    List<TournamentDTO> getSubscribedInTournaments(String jwt);

    List<TournamentDTO> getManagedTournaments(String jwt);

    List<TournamentDTO> getWatchlistTournaments(String jwt);

    boolean createTournament(String jwt, TournamentCreateDTO tournamentCreateDTO);
}
