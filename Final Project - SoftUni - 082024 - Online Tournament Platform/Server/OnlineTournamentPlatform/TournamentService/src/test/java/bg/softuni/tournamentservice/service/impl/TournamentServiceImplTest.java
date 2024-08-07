package bg.softuni.tournamentservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import bg.softuni.exceptionhandlerservice.ValidationException;
import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;


import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class TournamentServiceImplTest {

    private TournamentServiceImpl toTest;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private UserService userService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ValidationUtil validationUtil;

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
                teamRepository,
                validationUtil
        );
    }

    @Test
    void testIsValidTournamentData() {
        // Arrange
        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
        when(validationUtil.isValid(tournamentCreateDTO)).thenReturn(true);

        // Act
        boolean result = toTest.isValidTournamentData(tournamentCreateDTO);

        // Assert
        assertTrue(result);
        verify(validationUtil).isValid(tournamentCreateDTO);
    }

    @Test
    void testCreateTournament_ValidData() {
        // Arrange
        String jwt = "mock-jwt-token";
        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
        User user = new User();
        Game game = new Game();
        Tournament tournament = new Tournament();

        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
        when(userService.findUserByToken(jwt)).thenReturn(user);
        when(gameRepository.findByName(tournamentCreateDTO.getGame())).thenReturn(Optional.of(game));
        when(tournamentRepository.findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName())).thenReturn(Optional.empty());
        when(modelMapper.map(tournamentCreateDTO, Tournament.class)).thenReturn(tournament);

        // Act
        boolean result = toTest.createTournament(jwt, tournamentCreateDTO);

        // Assert
        assertTrue(result);
        verify(validationUtil).getViolations(tournamentCreateDTO);
        verify(userService).findUserByToken(jwt);
        verify(gameRepository).findByName(tournamentCreateDTO.getGame());
        verify(tournamentRepository).findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName());
        verify(tournamentRepository).save(tournamentCaptor.capture());

        Tournament capturedTournament = tournamentCaptor.getValue();
        assertNotNull(capturedTournament);
        assertEquals(user, capturedTournament.getManager());
        assertEquals(game, capturedTournament.getGame());
    }

    @Test
    void testCreateTournament_InvalidData() {
        // Arrange
        String jwt = "mock-jwt-token";
        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
        Set<ConstraintViolation<TournamentCreateDTO>> violations = mock(Set.class);
        String errorMessage = "Validation error message";

        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(violations);
        when(violations.isEmpty()).thenReturn(false);
        when(validationUtil.getFormattedErrorMessage(violations)).thenReturn(errorMessage);

        // Act & Assert
        bg.softuni.exceptionhandlerservice.ValidationException exception = assertThrows(bg.softuni.exceptionhandlerservice.ValidationException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
        assertEquals(errorMessage, exception.getMessage());
        verify(validationUtil).getViolations(tournamentCreateDTO);
        verify(validationUtil).getFormattedErrorMessage(violations);
    }

    @Test
    void testCreateTournament_InvalidUserToken() {
        // Arrange
        String jwt = "mock-jwt-token";
        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();

        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
        when(userService.findUserByToken(jwt)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
        assertEquals("Invalid user token", exception.getMessage());
        verify(userService).findUserByToken(jwt);
    }

    @Test
    void testCreateTournament_GameNotFound() {
        // Arrange
        String jwt = "mock-jwt-token";
        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
        User user = new User();

        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
        when(userService.findUserByToken(jwt)).thenReturn(user);
        when(gameRepository.findByName(tournamentCreateDTO.getGame())).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
        assertEquals("Game not found", exception.getMessage());
        verify(gameRepository).findByName(tournamentCreateDTO.getGame());
    }

    @Test
    void testCreateTournament_DuplicateTournament() {
        // Arrange
        String jwt = "mock-jwt-token";
        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
        User user = new User();
        Game game = new Game();
        Tournament existingTournament = new Tournament();

        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
        when(userService.findUserByToken(jwt)).thenReturn(user);
        when(gameRepository.findByName(tournamentCreateDTO.getGame())).thenReturn(Optional.of(game));
        when(tournamentRepository.findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName())).thenReturn(Optional.of(existingTournament));

        // Act & Assert
        DuplicateTournamentException exception = assertThrows(DuplicateTournamentException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
        assertEquals("A tournament with these unique fields already exists.", exception.getMessage());
        verify(tournamentRepository).findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName());
    }
}
