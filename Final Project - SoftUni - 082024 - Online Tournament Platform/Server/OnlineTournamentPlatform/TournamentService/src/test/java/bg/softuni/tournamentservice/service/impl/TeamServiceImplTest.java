package bg.softuni.tournamentservice.service.impl;

import bg.softuni.tournamentservice.model.ExportDto.TeamExportDTO;
import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.tournamentservice.service.TeamService;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMyTeams_shouldReturnTeamsForUser() {
        // Arrange
        String token = "some-token";
        User user = new User(); // Setup the User instance
        Team team = new Team(); // Setup the Team instance
        team.setId(1L);
        team.setName("Team A");
        List<Team> teams = Arrays.asList(team);

        when(tokenService.getUserByToken(token)).thenReturn(user);
        when(teamRepository.getAllByUsers(user)).thenReturn(teams);

        // Act
        List<TeamExportDTO> result = teamService.getMyTeams(token);

        // Assert
        assertNotNull(result);
        assertEquals(teams.size(), result.size());

        // Validate individual fields
        TeamExportDTO expectedDTO = convertToTeamExportDTO(team);
        TeamExportDTO actualDTO = result.get(0);

        assertEquals(expectedDTO.getId(), actualDTO.getId());
        assertEquals(expectedDTO.getName(), actualDTO.getName());
        assertEquals(expectedDTO.getMembers(), actualDTO.getMembers());
        assertEquals(expectedDTO.getUserIds(), actualDTO.getUserIds());
    }

    @Test
    void getTeamsByTournamentId_shouldReturnTeams() {
        // Arrange
        Long tournamentId = 1L;
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");

        TeamExportDTO teamDTO = TeamExportDTO.builder()
                .id(1L)
                .name("Team A")
                .members(Collections.emptyList())
                .userIds(Collections.emptyList())
                .build();

        when(teamRepository.findByTournamentId(tournamentId)).thenReturn(Collections.singletonList(team));

        // Act
        List<TeamExportDTO> teamDTOs = teamService.getTeamsByTournamentId(tournamentId, "someJwt");

        // Assert
        assertNotNull(teamDTOs);
        assertEquals(1, teamDTOs.size());

        TeamExportDTO actualDTO = teamDTOs.get(0);
        assertEquals(teamDTO.getId(), actualDTO.getId());
        assertEquals(teamDTO.getName(), actualDTO.getName());
        assertEquals(teamDTO.getMembers(), actualDTO.getMembers());
        assertEquals(teamDTO.getUserIds(), actualDTO.getUserIds());
    }

    @Test
    void getMyTeams_shouldThrowExceptionForInvalidToken() {
        // Arrange
        String invalidToken = "invalidToken";
        when(tokenService.getUserByToken(invalidToken)).thenThrow(new BadCredentialsException("Invalid token"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            teamService.getMyTeams(invalidToken);
        });
        assertEquals("Invalid token", thrown.getMessage());
    }

    // Mock implementation of convertToTeamExportDTO for test purposes
    private TeamExportDTO convertToTeamExportDTO(Team team) {
        return TeamExportDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .members(Collections.emptyList()) // Update as needed
                .userIds(Collections.emptyList()) // Update as needed
                .build();
    }
}
