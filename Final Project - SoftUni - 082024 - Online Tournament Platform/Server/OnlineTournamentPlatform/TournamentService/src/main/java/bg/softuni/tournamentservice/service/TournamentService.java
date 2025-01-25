package bg.softuni.tournamentservice.service;

import bg.softuni.tournamentservice.model.dto.TournamentSignupDTO;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.dto.TournamentDTO;

import java.util.List;

public interface TournamentService {

    List<TournamentDTO> getAllTournaments();

    List<TournamentDTO> getSubscribedInTournaments(String jwt);

    List<TournamentDTO> getManagedTournaments(String jwt);

    List<TournamentDTO> getWatchlistTournaments(String jwt);

    boolean createTournament(String jwt, TournamentCreateDTO tournamentCreateDTO);

    TournamentDTO getTournamentById(Long id, String jwt);

    boolean signupForTournament(String jwt, TournamentSignupDTO signupDTO);
}
