package bg.softuni.tournamentservice.service.impl;

import bg.softuni.tournamentservice.model.ExportDto.GameDTO;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.repository.AssetRepository;
import bg.softuni.tournamentservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private AssetRepository assetRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGames_shouldReturnGameDTOs() {
        // Arrange
        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("Game 1");

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("Game 2");

        List<Game> games = Arrays.asList(game1, game2);

        when(gameRepository.findAll()).thenReturn(games);

        Set<GameDTO> expectedGameDTOs = new TreeSet<>();
        expectedGameDTOs.add(createGameDTO(1L, "Game 1"));
        expectedGameDTOs.add(createGameDTO(2L, "Game 2"));

        // Act
        Set<GameDTO> actualGameDTOs = gameService.getAllGames();

        // Assert
        assertNotNull(actualGameDTOs);
        assertEquals(expectedGameDTOs.size(), actualGameDTOs.size());
        assertTrue(actualGameDTOs.containsAll(expectedGameDTOs));
    }

    private GameDTO createGameDTO(Long id, String title) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(id);
        gameDTO.setTitle(title);
        gameDTO.setAssets(Collections.emptyList()); // Use empty list or populate as needed
        return gameDTO;
    }
}
