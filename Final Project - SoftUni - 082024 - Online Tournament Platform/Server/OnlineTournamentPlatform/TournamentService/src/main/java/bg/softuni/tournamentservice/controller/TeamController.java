package bg.softuni.tournamentservice.controller;


import bg.softuni.tournamentservice.model.ExportDto.TeamExportDTO;
import bg.softuni.tournamentservice.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/my-teams")
    public ResponseEntity<List<TeamExportDTO>> getTeams(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }

        return ResponseEntity.ok(teamService.getMyTeams(jwt));
    }

    @PostMapping("/by-tournament")
    public ResponseEntity<List<TeamExportDTO>> getTeamsByTournament(@RequestBody Map<String, Long> payload, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }

        Long tournamentId = payload.get("tournamentId");
        return ResponseEntity.ok(teamService.getTeamsByTournamentId(tournamentId, jwt));
    }
}
