package bg.softuni.tournamentservice.controller;

import bg.softuni.tournamentservice.config.TestConfig;
import bg.softuni.tournamentservice.config.TestSecurityConfig;
import bg.softuni.tournamentservice.model.dto.TournamentSignupDTO;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.dto.TournamentDTO;
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

import java.text.SimpleDateFormat;
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
    private SimpleDateFormat dateFormat;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtToken = "Bearer mock-jwt-token";
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    @Test
    void testGetAllTournaments() throws Exception {
        Date now = new Date();
        TournamentDTO tournament = new TournamentDTO()
                .setId(1L)
                .setName("Tournament A")
                .setStartDate(now)
                .setEndDate(now);

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
        Date now = new Date();
        TournamentDTO tournament = new TournamentDTO()
                .setId(1L)
                .setName("Tournament A")
                .setStartDate(now)
                .setEndDate(now);

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
        Date now = new Date();
        TournamentDTO tournament = new TournamentDTO()
                .setId(1L)
                .setName("Tournament A")
                .setStartDate(now)
                .setEndDate(now);

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
        Date now = new Date();
        Date tomorrow = new Date(now.getTime() + 24 * 60 * 60 * 1000); // 1 day later

        String jsonContent = String.format("{\"name\":\"New Tournament\",\"game\":\"Soccer\",\"category\":\"Sports\",\"summary\":\"A new exciting soccer tournament\",\"startDate\":\"%s\",\"endDate\":\"%s\",\"numberOfTeams\":16,\"teamSize\":5}",
                dateFormat.format(now), dateFormat.format(tomorrow));

        Mockito.when(tournamentService.createTournament(Mockito.eq("mock-jwt-token"), Mockito.any(TournamentCreateDTO.class))).thenReturn(true);

        mockMvc.perform(post("/api/tournaments/create")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    void testGetTournamentById() throws Exception {
        Date now = new Date();
        TournamentDTO tournament = new TournamentDTO()
                .setId(1L)
                .setName("Tournament A")
                .setStartDate(now)
                .setEndDate(now);

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

        Mockito.when(tournamentService.signupForTournament(Mockito.eq("mock-jwt-token"), Mockito.any(TournamentSignupDTO.class)))
                .thenReturn(true);

        mockMvc.perform(post("/api/tournaments/signup")
                        .header(HttpHeaders.AUTHORIZATION, jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tournamentId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
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
