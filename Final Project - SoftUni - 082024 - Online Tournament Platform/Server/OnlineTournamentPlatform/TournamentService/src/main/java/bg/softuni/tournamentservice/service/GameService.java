package bg.softuni.tournamentservice.service;


import bg.softuni.tournamentservice.model.ExportDto.GameDTO;
import bg.softuni.tournamentservice.model.ExportDto.GameTitleDTO;
import bg.softuni.tournamentservice.model.Game;

import java.util.List;
import java.util.Set;

public interface GameService  {
    GameDTO mapToGameDTO(Game game);

    List<Game> findAll();

    Set<GameTitleDTO> getAllGames();

    ;
}
