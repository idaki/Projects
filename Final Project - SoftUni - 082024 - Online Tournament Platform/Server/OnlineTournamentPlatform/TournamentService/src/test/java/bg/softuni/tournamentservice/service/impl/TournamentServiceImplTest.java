package bg.softuni.tournamentservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.ExportDto.TournamentSignupDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentCreateDTO;
import bg.softuni.tournamentservice.model.viewDto.TournamentDTO;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class TournamentServiceImplTest {

    private TournamentServiceImpl toTest;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private UserService userService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private GameRepository gameRepository; // Ensure GameRepository is used correctly

    @Mock
    private ModelMapper modelMapper;

    @Captor
    private ArgumentCaptor<Tournament> tournamentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        toTest = new TournamentServiceImpl(
                tournamentRepository,
                userService,
                gameRepository,
                modelMapper,
                teamRepository
        );
    }

    @Test
    void testGetTournamentById() {
        // Arrange
        Long tournamentId = 1L;
        Tournament tournament = new Tournament();
        TournamentDTO tournamentDTO = TournamentDTO.builder().id(tournamentId).build();
        when(tournamentRepository.findById(tournamentId)).thenReturn(Optional.of(tournament));
        when(modelMapper.map(tournament, TournamentDTO.class)).thenReturn(tournamentDTO);

        // Act
        TournamentDTO result = toTest.getTournamentById(tournamentId, "mock-jwt");

        // Assert
        assertNotNull(result);
        assertEquals(tournamentDTO, result);
    }

    @Test
    void testSignupForTournament() {
        // Arrange
        String jwt = "mock-jwt-token";
        TournamentSignupDTO signupDTO = TournamentSignupDTO.builder()
                .tournamentId(1L)
                .teamName("TeamName")
                .build();

        User user = new User();
        Tournament tournament = new Tournament();
        Team team = new Team();

        when(userService.findUserByToken(jwt)).thenReturn(user);
        when(tournamentRepository.findById(signupDTO.getTournamentId())).thenReturn(Optional.of(tournament));
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        // Act
        boolean result = toTest.signupForTournament(jwt, signupDTO);

        // Assert
        assertTrue(result);
        verify(teamRepository).save(any(Team.class));
        verify(tournamentRepository).save(tournamentCaptor.capture()); // Verify if the tournament is saved correctly
    }



    @Test
    void testGetManagedTournaments() {
        // Arrange
        String jwt = "mock-jwt-token";
        User user = new User();
        user.setId(1L);
        List<Tournament> tournaments = List.of(new Tournament());
        List<TournamentDTO> tournamentDTOs = List.of(TournamentDTO.builder().build());

        when(userService.findUserByToken(jwt)).thenReturn(user);
        when(tournamentRepository.findByManagerId(user.getId())).thenReturn(tournaments);
        when(modelMapper.map(tournaments.get(0), TournamentDTO.class)).thenReturn(tournamentDTOs.get(0));

        // Act
        List<TournamentDTO> result = toTest.getManagedTournaments(jwt);

        // Assert
        assertNotNull(result);
        assertEquals(tournamentDTOs, result);
    }

    @Test
    void testGetWatchlistTournaments() {
        // Arrange
        String jwt = "mock-jwt-token";
        User user = new User();
        user.setId(1L);
        List<Tournament> tournaments = List.of(new Tournament());
        List<TournamentDTO> tournamentDTOs = List.of(TournamentDTO.builder().build());

        when(userService.findUserByToken(jwt)).thenReturn(user);
        when(tournamentRepository.findByFollowerId(user.getId())).thenReturn(tournaments);
        when(modelMapper.map(tournaments.get(0), TournamentDTO.class)).thenReturn(tournamentDTOs.get(0));

        // Act
        List<TournamentDTO> result = toTest.getWatchlistTournaments(jwt);

        // Assert
        assertNotNull(result);
        assertEquals(tournamentDTOs, result);
    }


}
