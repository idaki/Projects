package bg.softuni.gameservice.service;

import bg.softuni.crudservice.crud.CrudService;
import bg.softuni.gameservice.model.ExportDto.GameDTO;
import bg.softuni.gameservice.model.ExportDto.GameTitleDTO;
import bg.softuni.gameservice.model.Game;

import java.util.Set;

public interface GameService extends CrudService<Game, Long> {

    GameDTO mapToGameDTO(Game game);

    Set<GameTitleDTO> getAllGames();
}
