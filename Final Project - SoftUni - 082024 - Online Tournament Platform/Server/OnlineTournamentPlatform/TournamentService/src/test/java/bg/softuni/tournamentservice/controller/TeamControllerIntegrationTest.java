package bg.softuni.tournamentservice.controller;

import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import bg.softuni.tournamentservice.config.TestConfig;
import bg.softuni.tournamentservice.config.TestSecurityConfig;
import bg.softuni.tournamentservice.model.dto.TeamExportDTO;
import bg.softuni.tournamentservice.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, TestSecurityConfig.class})
@AutoConfigureMockMvc
public class TeamControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    private String jwtToken;
    @MockBean
    private ValidationUtil validationUtil;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtToken = "Bearer mock-jwt-token";
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetTeams() throws Exception {
        TeamExportDTO team = TeamExportDTO.builder()
                .id(1L)
                .name("Team A")
                .members(List.of("User1", "User2"))
                .userIds(List.of(1L, 2L))
                .build();

        Mockito.when(teamService.getMyTeams("mock-jwt-token")).thenReturn(List.of(team));

        mockMvc.perform(post("/api/teams/my-teams")
                        .header("Authorization", jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Team A")))
                .andExpect(jsonPath("$[0].members[0]", is("User1")))
                .andExpect(jsonPath("$[0].members[1]", is("User2")))
                .andExpect(jsonPath("$[0].userIds[0]", is(1)))
                .andExpect(jsonPath("$[0].userIds[1]", is(2)));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetTeamsByTournament() throws Exception {
        TeamExportDTO team = TeamExportDTO.builder()
                .id(1L)
                .name("Team A")
                .members(List.of("User1", "User2"))
                .userIds(List.of(1L, 2L))
                .build();

        Mockito.when(teamService.getTeamsByTournamentId(Mockito.eq(1L), Mockito.any(String.class))).thenReturn(List.of(team));

        mockMvc.perform(post("/api/teams/by-tournament")
                        .header("Authorization", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tournamentId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Team A")))
                .andExpect(jsonPath("$[0].members[0]", is("User1")))
                .andExpect(jsonPath("$[0].members[1]", is("User2")))
                .andExpect(jsonPath("$[0].userIds[0]", is(1)))
                .andExpect(jsonPath("$[0].userIds[1]", is(2)));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetTeamsWithEmptyJwt() throws Exception {
        mockMvc.perform(post("/api/teams/my-teams")
                        .header("Authorization", "Bearer "))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetTeamsByTournamentWithEmptyJwt() throws Exception {
        mockMvc.perform(post("/api/teams/by-tournament")
                        .header("Authorization", "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tournamentId\":1}"))
                .andExpect(status().isBadRequest());
    }
}
