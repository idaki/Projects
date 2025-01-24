package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.dto.GameDTO;

import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.repository.AssetRepository;
import bg.softuni.tournamentservice.repository.GameRepository;
import bg.softuni.tournamentservice.service.GameService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;


    public GameServiceImpl(GameRepository gameRepository) {

        this.gameRepository = gameRepository;

    }


    @Override
    public Set<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();

        Set<GameDTO> gameTitleListDTOs = new TreeSet<>();

        for (Game game : games) {
            GameDTO gameTitleDTO = new GameDTO();
            gameTitleDTO.setTitle(game.getName());
            gameTitleDTO.setId(game.getId());
            gameTitleListDTOs.add(gameTitleDTO);
        }

        return gameTitleListDTOs;
    }

}


