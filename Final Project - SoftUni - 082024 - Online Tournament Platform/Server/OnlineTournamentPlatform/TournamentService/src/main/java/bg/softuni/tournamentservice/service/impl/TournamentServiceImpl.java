package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.utils.Builders.TeamBuilder.TeamBuilderImpl;

import bg.softuni.tournamentservice.utils.Builders.TournamentBuilder.TournamentBuilderImpl;

import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.dto.*;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.tournamentservice.service.TournamentService;
import bg.softuni.tournamentservice.utils.TouurnamentValidator.TournamentValidator;
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
    private  TournamentValidator tournamentValidator;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, ModelMapper modelMapper,
                                 TeamRepository teamRepository, TournamentValidator tournamentValidator) {
        this.tournamentRepository = tournamentRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;

    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        return tournamentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getSubscribedInTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt);  // Authenticate the user
        return tournamentRepository.findSubscribedTournaments(user.getId()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getManagedTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt);
        return tournamentRepository.findByManagerId(user.getId()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getWatchlistTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt);
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

        Tournament tournament = new TournamentBuilderImpl()
                .withName(tournamentCreateDTO.getName())
                .withCategory(tournamentCreateDTO.getCategory())
                .withSummary(tournamentCreateDTO.getSummary())
                .withStartDate(tournamentCreateDTO.getStartDate())
                .withEndDate(tournamentCreateDTO.getEndDate())
                .withNumberOfTeams(tournamentCreateDTO.getNumberOfTeams())
                .withTeamSize(tournamentCreateDTO.getTeamSize())
                .withManager(user)
                .withGame(game)
                .build();

        tournamentRepository.save(tournament);
        return true;
    }

    private TournamentDTO convertToDto(Tournament tournament) {
        return modelMapper.map(tournament, TournamentDTO.class);
    }

    @Override
    public TournamentDTO getTournamentById(Long id, String jwt) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return convertToDto(tournament);
    }

    @Override
    public boolean signupForTournament(String jwt, TournamentSignupDTO signupDTO) {
        User user = tournamentValidator.authenticateUser(jwt); // Authenticate user
        Tournament tournament = tournamentRepository.findById(signupDTO.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        Team team = new TeamBuilderImpl()
                .withTournament(tournament)
                .withTeamName(signupDTO.getTeamName())
                .withManager(user)
                .build();

        teamRepository.save(team);
        return true;
    }
}
