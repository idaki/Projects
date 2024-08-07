package bg.softuni.tournamentservice.controller;

import bg.softuni.tournamentservice.config.TestConfig;
import bg.softuni.tournamentservice.config.TestSecurityConfig;
import bg.softuni.tournamentservice.model.dto.AssetDTO;
import bg.softuni.tournamentservice.model.dto.GameDTO;
import bg.softuni.tournamentservice.service.GameService;
import bg.softuni.exceptionhandlerservice.utils.ValidationUtil; // Add this import
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, TestSecurityConfig.class})
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private ValidationUtil validationUtil; // Add this mock

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGames() throws Exception {
        // Create sample AssetDTO objects
        AssetDTO asset1 = new AssetDTO();
        asset1.setId(1L);
        asset1.setName("Ball");

        AssetDTO asset2 = new AssetDTO();
        asset2.setId(2L);
        asset2.setName("Goal");

        // Create sample GameDTO objects
        GameDTO game1 = new GameDTO();
        game1.setId(1L);
        game1.setTitle("Soccer");
        game1.setAssets(List.of(asset1, asset2));

        GameDTO game2 = new GameDTO();
        game2.setId(2L);
        game2.setTitle("Basketball");
        game2.setAssets(List.of(asset1));

        // Mock the service call
        Mockito.when(gameService.getAllGames()).thenReturn(Set.of(game1, game2));

        // Perform the GET request and assert the response
        mockMvc.perform(get("/api/games")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-jwt-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].title", containsInAnyOrder("Soccer", "Basketball")));
    }
}
