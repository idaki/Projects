package bg.softuni.tournamentservice.controller;


import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.service.TournamentService;
import bg.softuni.userservice.models.dto.FriendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/all")
    public List<TournamentDTO> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public TournamentDTO getTournamentById(@PathVariable Long id) {
        return null;   // Implement this method if needed
    }

    @PostMapping
    public TournamentDTO createTournament(@RequestBody TournamentDTO tournamentDTO) {
        return null;   // Implement this method if needed
    }

    @PutMapping("/{id}")
    public TournamentDTO updateTournament(@PathVariable Long id, @RequestBody TournamentDTO tournamentDTO) {
     return null;   // Implement this method if needed
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        // Implement this method if needed
    }

    @PostMapping("/managed")
    public ResponseEntity<List<TournamentDTO>> getMyTournaments(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }
        return ResponseEntity.ok(tournamentService.getManagedTournaments(jwt));
    }
}
