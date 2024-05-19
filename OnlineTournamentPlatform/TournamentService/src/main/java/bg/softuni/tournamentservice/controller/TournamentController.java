package bg.softuni.tournamentservice.controller;

import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<List<TournamentDTO>> getAllTournaments() {
        List<TournamentDTO> tournaments = tournamentService.getAllTournaments();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
//        Tournament tournament = tournamentService.findById(id).orElse(null);
//        if (tournament != null) {
//            return new ResponseEntity<>(tournament, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
//        Tournament createdTournament = tournamentService.save(tournament);
//        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
//        Tournament existingTournament = tournamentService.findById(id).orElse(null);
//        if (existingTournament != null) {
//            Tournament updatedTournament = tournamentService.save(tournament);
//            return new ResponseEntity<>(updatedTournament, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
//        Tournament existingTournament = tournamentService.findById(id).orElse(null);
//        if (existingTournament != null) {
//            tournamentService.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
