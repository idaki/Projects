package bg.softuni.gameservice.controller;

import bg.softuni.gameservice.service.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameController implements CommandLineRunner {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
