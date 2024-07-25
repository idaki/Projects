package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.ExportDto.GameDTO;

import bg.softuni.tournamentservice.model.ExportDto.GameTitleDTO;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.repository.AssetRepository;
import bg.softuni.tournamentservice.repository.GameRepository;
import bg.softuni.tournamentservice.service.GameService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final AssetRepository assetRepository;

    public GameServiceImpl(GameRepository gameRepository, AssetRepository assetRepository) {

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

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Set<GameTitleDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();
        Set<GameTitleDTO> gameTitleListDTOs = new TreeSet<>();
        games.forEach(game -> {
            GameTitleDTO gameTitleDTO = new GameTitleDTO();
            gameTitleDTO.setTitle(game.getTitle());
            gameTitleListDTOs.add(gameTitleDTO);

        });

        System.out.println();
        return gameTitleListDTOs;
    }

}


