package bg.softuni.tournamentservice.controller;


import bg.softuni.tournamentservice.model.dto.GameDTO;
import bg.softuni.tournamentservice.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/api/games")
    public Set<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }
}
