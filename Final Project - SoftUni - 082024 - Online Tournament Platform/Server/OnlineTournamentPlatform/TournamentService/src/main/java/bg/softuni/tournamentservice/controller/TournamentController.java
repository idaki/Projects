package bg.softuni.tournamentservice.controller;

import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
import bg.softuni.exceptionhandlerservice.ValidationException;
import bg.softuni.tournamentservice.model.dto.TournamentSignupDTO;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.dto.TournamentDTO;
import bg.softuni.tournamentservice.service.TournamentService;
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
    public ResponseEntity<String> createTournament(@RequestHeader("Authorization") String jwt, @RequestBody TournamentCreateDTO tournamentCreateDTO) {
        if (jwt == null || jwt.length() < 8) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token");
        }

        jwt = jwt.substring(7);

        try {
            tournamentService.createTournament(jwt, tournamentCreateDTO);
            return ResponseEntity.ok("Tournament created successfully");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DuplicateTournamentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/public/tournaments/details")
    public ResponseEntity<TournamentDTO> getTournamentById(@RequestBody Long id) {
        TournamentDTO tournament = tournamentService.getTournamentById(id, null); // Remove the JWT parameter
        if (tournament == null) {
            return ResponseEntity.notFound().build(); // Return 404 if tournament is not found
        }
        return ResponseEntity.ok(tournament);
    }

    @PostMapping("/tournaments/signup")
    public ResponseEntity<Boolean> signupForTournament(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TournamentSignupDTO signupDTO) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }
        boolean isSignedUp = tournamentService.signupForTournament(jwt, signupDTO);
        return ResponseEntity.ok(isSignedUp);
    }
}
