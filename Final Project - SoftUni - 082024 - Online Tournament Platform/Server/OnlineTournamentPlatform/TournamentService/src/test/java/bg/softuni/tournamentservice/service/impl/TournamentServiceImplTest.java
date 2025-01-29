//package bg.softuni.tournamentservice.service.impl;
//
//import bg.softuni.exceptionhandlerservice.DuplicateTournamentException;
//import bg.softuni.exceptionhandlerservice.ValidationException;
//import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
//import bg.softuni.tournamentservice.model.*;
//import bg.softuni.tournamentservice.model.dto.TournamentCreateDTO;
//import bg.softuni.tournamentservice.model.dto.TournamentDTO;
//import bg.softuni.tournamentservice.model.dto.TournamentSignupDTO;
//import bg.softuni.tournamentservice.repository.*;
//import bg.softuni.userservice.models.entity.user.User;
//import bg.softuni.userservice.service.interfaces.user.UserService;
//import jakarta.validation.ConstraintViolation;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.modelmapper.ModelMapper;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class TournamentServiceImplTest {
//
//    private TournamentServiceImpl toTest;
//
//    @Mock
//    private TournamentRepository tournamentRepository;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private TeamRepository teamRepository;
//
//    @Mock
//    private GameRepository gameRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private ValidationUtil validationUtil;
//
//    @Captor
//    private ArgumentCaptor<Tournament> tournamentCaptor;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        toTest = new TournamentServiceImpl(
//                tournamentRepository,
//                userService,
//                gameRepository,
//                modelMapper,
//                teamRepository,
//                validationUtil
//        );
//    }
//
//    @Test
//    void testIsValidTournamentData() {
//        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
//        when(validationUtil.isValid(tournamentCreateDTO)).thenReturn(true);
//
//        boolean result = toTest.isValidTournamentData(tournamentCreateDTO);
//
//        assertTrue(result);
//        verify(validationUtil).isValid(tournamentCreateDTO);
//    }
//
//    @Test
//    void testCreateTournament_ValidData() {
//        String jwt = "mock-jwt-token";
//        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
//        User user = new User();
//        Game game = new Game();
//        Tournament tournament = new Tournament();
//
//        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
//        when(userService.findUserByToken(jwt)).thenReturn(user);
//        when(gameRepository.findByName(tournamentCreateDTO.getGame())).thenReturn(Optional.of(game));
//        when(tournamentRepository.findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName())).thenReturn(Optional.empty());
//        when(modelMapper.map(tournamentCreateDTO, Tournament.class)).thenReturn(tournament);
//
//        boolean result = toTest.createTournament(jwt, tournamentCreateDTO);
//
//        assertTrue(result);
//        verify(validationUtil).getViolations(tournamentCreateDTO);
//        verify(userService).findUserByToken(jwt);
//        verify(gameRepository).findByName(tournamentCreateDTO.getGame());
//        verify(tournamentRepository).findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName());
//        verify(tournamentRepository).save(tournamentCaptor.capture());
//
//        Tournament capturedTournament = tournamentCaptor.getValue();
//        assertNotNull(capturedTournament);
//        assertEquals(user, capturedTournament.getManager());
//        assertEquals(game, capturedTournament.getGame());
//    }
//
//    @Test
//    void testCreateTournament_InvalidData() {
//        String jwt = "mock-jwt-token";
//        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
//        Set<ConstraintViolation<TournamentCreateDTO>> violations = mock(Set.class);
//        String errorMessage = "Validation error message";
//
//        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(violations);
//        when(violations.isEmpty()).thenReturn(false);
//        when(validationUtil.getFormattedErrorMessage(violations)).thenReturn(errorMessage);
//
//        ValidationException exception = assertThrows(ValidationException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
//        assertEquals(errorMessage, exception.getMessage());
//        verify(validationUtil).getViolations(tournamentCreateDTO);
//        verify(validationUtil).getFormattedErrorMessage(violations);
//    }
//
//    @Test
//    void testCreateTournament_InvalidUserToken() {
//        String jwt = "mock-jwt-token";
//        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
//
//        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
//        when(userService.findUserByToken(jwt)).thenReturn(null);
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
//        assertEquals("Invalid user token", exception.getMessage());
//        verify(userService).findUserByToken(jwt);
//    }
//
//    @Test
//    void testCreateTournament_GameNotFound() {
//        String jwt = "mock-jwt-token";
//        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
//        User user = new User();
//
//        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
//        when(userService.findUserByToken(jwt)).thenReturn(user);
//        when(gameRepository.findByName(tournamentCreateDTO.getGame())).thenReturn(Optional.empty());
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
//        assertEquals("Game not found", exception.getMessage());
//        verify(gameRepository).findByName(tournamentCreateDTO.getGame());
//    }
//
//    @Test
//    void testCreateTournament_DuplicateTournament() {
//        String jwt = "mock-jwt-token";
//        TournamentCreateDTO tournamentCreateDTO = new TournamentCreateDTO();
//        User user = new User();
//        Game game = new Game();
//        Tournament existingTournament = new Tournament();
//
//        when(validationUtil.getViolations(tournamentCreateDTO)).thenReturn(Collections.emptySet());
//        when(userService.findUserByToken(jwt)).thenReturn(user);
//        when(gameRepository.findByName(tournamentCreateDTO.getGame())).thenReturn(Optional.of(game));
//        when(tournamentRepository.findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName())).thenReturn(Optional.of(existingTournament));
//
//        DuplicateTournamentException exception = assertThrows(DuplicateTournamentException.class, () -> toTest.createTournament(jwt, tournamentCreateDTO));
//        assertEquals("A tournament with these unique fields already exists.", exception.getMessage());
//        verify(tournamentRepository).findByGameAndManagerAndName(game, user, tournamentCreateDTO.getName());
//    }
//
//    @Test
//    void testGetAllTournaments() {
//        Tournament tournament = new Tournament();
//        TournamentDTO tournamentDTO = new TournamentDTO();
//        when(tournamentRepository.findAll()).thenReturn(List.of(tournament));
//        when(modelMapper.map(any(Tournament.class), eq(TournamentDTO.class))).thenReturn(tournamentDTO);
//
//        List<TournamentDTO> result = toTest.getAllTournaments();
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        verify(tournamentRepository).findAll();
//    }
//
//    @Test
//    void testGetSubscribedInTournaments() {
//        String jwt = "dummyJwt";
//        when(userService.findUserByToken(jwt)).thenReturn(new User());
//
//        List<TournamentDTO> result = toTest.getSubscribedInTournaments(jwt);
//
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//        verify(userService).findUserByToken(jwt);
//    }
//
//    @Test
//    void testGetManagedTournaments() {
//        String jwt = "dummyJwt";
//        User user = new User();
//        Tournament tournament = new Tournament();
//        TournamentDTO tournamentDTO = new TournamentDTO();
//        when(userService.findUserByToken(jwt)).thenReturn(user);
//        when(tournamentRepository.findByManagerId(user.getId())).thenReturn(List.of(tournament));
//        when(modelMapper.map(any(Tournament.class), eq(TournamentDTO.class))).thenReturn(tournamentDTO);
//
//        List<TournamentDTO> result = toTest.getManagedTournaments(jwt);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        verify(userService).findUserByToken(jwt);
//        verify(tournamentRepository).findByManagerId(user.getId());
//    }
//
//    @Test
//    void testGetWatchlistTournaments() {
//        String jwt = "dummyJwt";
//        User user = new User();
//        Tournament tournament = new Tournament();
//        TournamentDTO tournamentDTO = new TournamentDTO();
//        when(userService.findUserByToken(jwt)).thenReturn(user);
//        when(tournamentRepository.findByFollowerId(user.getId())).thenReturn(List.of(tournament));
//        when(modelMapper.map(any(Tournament.class), eq(TournamentDTO.class))).thenReturn(tournamentDTO);
//
//        List<TournamentDTO> result = toTest.getWatchlistTournaments(jwt);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        verify(userService).findUserByToken(jwt);
//        verify(tournamentRepository).findByFollowerId(user.getId());
//    }
//
//    @Test
//    void testGetTournamentById() {
//        Long id = 1L;
//        String jwt = "dummyJwt";
//        Tournament tournament = new Tournament();
//        TournamentDTO tournamentDTO = new TournamentDTO();
//        when(tournamentRepository.findById(id)).thenReturn(Optional.of(tournament));
//        when(modelMapper.map(any(Tournament.class), eq(TournamentDTO.class))).thenReturn(tournamentDTO);
//
//        TournamentDTO result = toTest.getTournamentById(id, jwt);
//
//        assertNotNull(result);
//        verify(tournamentRepository).findById(id);
//    }
//
//    @Test
//    void testSignupForTournament() {
//        String jwt = "dummyJwt";
//        TournamentSignupDTO signupDTO = new TournamentSignupDTO();
//        signupDTO.setTournamentId(1L);
//        signupDTO.setTeamName("Team A");
//        User user = new User();
//        Tournament tournament = new Tournament();
//
//        when(userService.findUserByToken(jwt)).thenReturn(user);
//        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
//
//        boolean result = toTest.signupForTournament(jwt, signupDTO);
//
//        assertTrue(result);
//        verify(userService).findUserByToken(jwt);
//        verify(tournamentRepository).findById(1L);
//        verify(teamRepository).save(any(Team.class));
//        verify(tournamentRepository).save(any(Tournament.class));
//    }
//}
