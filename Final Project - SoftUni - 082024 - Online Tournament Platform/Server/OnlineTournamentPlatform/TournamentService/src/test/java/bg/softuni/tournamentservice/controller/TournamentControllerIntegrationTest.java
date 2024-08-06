package bg.softuni.tournamentservice.controller;

import bg.softuni.tournamentservice.config.TestConfig;
import bg.softuni.tournamentservice.config.TestSecurityConfig;
import bg.softuni.tournamentservice.model.ExportDto.TournamentSignupDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.service.TournamentService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, TestSecurityConfig.class})
@AutoConfigureMockMvc
public class TournamentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    private String jwtToken;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtToken = "Bearer mock-jwt-token";
    }

    @Test
    void testGetAllTournaments() throws Exception {
        TournamentDTO tournament = TournamentDTO.builder()
                .id(1L)
                .name("Tournament A")
                .build();

        Mockito.when(tournamentService.getAllTournaments()).thenReturn(List.of(tournament));

        mockMvc.perform(get("/api/tournaments/all")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Tournament A")));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetManagedTournaments() throws Exception {
        TournamentDTO tournament = TournamentDTO.builder()
                .id(1L)
                .name("Tournament A")
                .build();

        Mockito.when(tournamentService.getManagedTournaments("mock-jwt-token")).thenReturn(List.of(tournament));

        mockMvc.perform(post("/api/tournaments/managed")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Tournament A")));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetWatchlist() throws Exception {
        TournamentDTO tournament = TournamentDTO.builder()
                .id(1L)
                .name("Tournament A")
                .build();

        Mockito.when(tournamentService.getWatchlistTournaments("mock-jwt-token")).thenReturn(List.of(tournament));

        mockMvc.perform(post("/api/tournaments/watchlist")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Tournament A")));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testCreateTournament() throws Exception {
        // Create a TournamentCreateDTO object with LocalDate
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        // Create JSON string with correct date format
        String jsonContent = String.format("{\"name\":\"New Tournament\",\"game\":\"Soccer\",\"category\":\"Sports\",\"summary\":\"A new exciting soccer tournament\",\"startDate\":\"%s\",\"endDate\":\"%s\",\"numberOfTeams\":16,\"teamSize\":5}",
                startDate.toString(), endDate.toString());

        // Mock the service call
        Mockito.when(tournamentService.createTournament(Mockito.eq("mock-jwt-token"), Mockito.any(TournamentCreateDTO.class))).thenReturn(true);

        // Perform the POST request and assert the response
        mockMvc.perform(post("/api/tournaments/create")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    void testGetTournamentById() throws Exception {
        TournamentDTO tournament = TournamentDTO.builder()
                .id(1L)
                .name("Tournament A")
                .build();

        Mockito.when(tournamentService.getTournamentById(1L, null)).thenReturn(tournament);

        mockMvc.perform(post("/api/public/tournaments/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Tournament A")));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testSignupForTournament() throws Exception {
        TournamentSignupDTO signupDTO = TournamentSignupDTO.builder()
                .tournamentId(1L)
                .build();

        // Mock the service call to return true
        Mockito.when(tournamentService.signupForTournament(Mockito.eq("mock-jwt-token"), Mockito.any(TournamentSignupDTO.class)))
                .thenReturn(true);

        // Perform the POST request and assert the response
        mockMvc.perform(post("/api/tournaments/signup")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tournamentId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));  // Ensure the response is expected as true
    }
    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetManagedTournamentsWithEmptyJwt() throws Exception {
        mockMvc.perform(post("/api/tournaments/managed")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetWatchlistWithEmptyJwt() throws Exception {
        mockMvc.perform(post("/api/tournaments/watchlist")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testCreateTournamentWithEmptyJwt() throws Exception {
        mockMvc.perform(post("/api/tournaments/create")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Tournament\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetTournamentByIdWithInvalidId() throws Exception {
        Mockito.when(tournamentService.getTournamentById(1L, null)).thenReturn(null);

        mockMvc.perform(post("/api/public/tournaments/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isNotFound());
    }
}
