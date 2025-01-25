package bg.softuni.tournamentservice.service.impl;

import bg.softuni.tournamentservice.utils.TouurnamentValidator.TournamentValidator;
import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.dto.*;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.tournamentservice.service.TournamentService;
import bg.softuni.tournamentservice.utils.Factory.TournamentDTO.TournamentDTOConverterFactory;
import bg.softuni.userservice.models.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final TournamentValidator tournamentValidator;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, ModelMapper modelMapper,
                                 TeamRepository teamRepository, TournamentValidator tournamentValidator) {
        this.tournamentRepository = tournamentRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.tournamentValidator = tournamentValidator;
    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        return tournamentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getSubscribedInTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt); // Authenticate user
        // Placeholder for the actual subscribed logic
        return List.of(); // Return an empty list for now
    }

    @Override
    public List<TournamentDTO> getManagedTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt); // Authenticate user
        return tournamentRepository.findByManagerId(user.getId()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getWatchlistTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt); // Authenticate user
        return tournamentRepository.findByFollowerId(user.getId()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createTournament(String jwt, TournamentCreateDTO tournamentCreateDTO) {
        tournamentValidator.validateTournamentData(tournamentCreateDTO);
        User user = tournamentValidator.authenticateUser(jwt);
        Game game = tournamentValidator.validateGameExistence(tournamentCreateDTO.getGame());
        tournamentValidator.checkForDuplicateTournament(game, user, tournamentCreateDTO.getName());
        createAndSaveTournament(tournamentCreateDTO, user, game);
        return true;
    }

    private Tournament createAndSaveTournament(TournamentCreateDTO tournamentCreateDTO, User user, Game game) {
        Tournament tournament = modelMapper.map(tournamentCreateDTO, Tournament.class);
        tournament.setManager(user);
        tournament.setGame(game);
        tournamentRepository.save(tournament);
        return tournament;
    }

    private TournamentDTO convertToDto(Tournament tournament) {
        TournamentDTOConverterFactory factory = new TournamentDTOConverterFactory(modelMapper);
        return factory.convert(tournament);
    }

    @Override
    public TournamentDTO getTournamentById(Long id, String jwt) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return modelMapper.map(tournament, TournamentDTO.class);
    }

    @Override
    public boolean signupForTournament(String jwt, TournamentSignupDTO signupDTO) {
        User user = tournamentValidator.authenticateUser(jwt); // Authenticate user
        Tournament tournament = tournamentRepository.findById(signupDTO.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        createAndSaveTeam(signupDTO, user, tournament);
        return true;
    }

    private Team createAndSaveTeam(TournamentSignupDTO signupDTO, User user, Tournament tournament) {
        Team team = new Team(signupDTO.getTeamName(), 10); // Assume team capacity is 10
        team.setManager(user);
        team.setTournament(tournament);
        teamRepository.save(team);
        tournament.getTeams().add(team);
        tournamentRepository.save(tournament);
        return team;
    }
}
