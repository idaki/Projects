package bg.softuni.tournamentservice.controller;


import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.service.TournamentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournaments/all")
    public List<TournamentDTO> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping("/tournaments/managed")
    public ResponseEntity<List<TournamentDTO>> getMyTournaments(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }
        return ResponseEntity.ok(tournamentService.getManagedTournaments(jwt));
    }

    @PostMapping("/tournaments/watchlist")
    public ResponseEntity<List<TournamentDTO>> getWatchlist(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }
        return ResponseEntity.ok(tournamentService.getWatchlistTournaments(jwt));
    }

    @PostMapping("/tournaments/create")
    public ResponseEntity<Boolean> createTournament(@RequestHeader("Authorization") String jwt, @RequestBody TournamentCreateDTO tournamentCreateDTO) {
        jwt = jwt.substring(7);
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }
        boolean isCreated = tournamentService.createTournament(jwt, tournamentCreateDTO);
        return ResponseEntity.ok(isCreated);
    }

    @PostMapping("/public/tournaments/details")
    public ResponseEntity<TournamentDTO> getTournamentById(@RequestBody Long id) {
        TournamentDTO tournament = tournamentService.getTournamentById(id, null); // Remove the JWT parameter
        if (tournament == null) {
            return ResponseEntity.notFound().build(); // Return 404 if tournament is not found
        }
        return ResponseEntity.ok(tournament);
    }
}
