package bg.softuni.gameservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.gameservice.model.Asset;
import bg.softuni.gameservice.model.ExportDto.GameDTO;
import bg.softuni.gameservice.model.Game;
import bg.softuni.gameservice.repository.AssetRepository;
import bg.softuni.gameservice.repository.GameRepository;
import bg.softuni.gameservice.service.GameService;
import org.springframework.stereotype.Service;



@Service
public class GameServiceImpl extends CrudServiceImpl<Game, Long> implements GameService {

    private final GameRepository gameRepository;
    private final AssetRepository assetRepository;

    public GameServiceImpl(GameRepository gameRepository, AssetRepository assetRepository) {
        super(gameRepository);
        this.gameRepository = gameRepository;
        this.assetRepository = assetRepository;
    }


    @Override
    public GameDTO mapToGameDTO(Game game) {
        if (game == null) {
            return null;
        }

        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        // Map other properties as needed
        return gameDTO;
    }
}


