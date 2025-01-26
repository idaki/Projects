package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.utils.Builders.TeamBuilder.TeamBuilderImpl;

import bg.softuni.tournamentservice.utils.Builders.TournamentBuilder.TournamentBuilderImpl;

import bg.softuni.tournamentservice.model.*;
import bg.softuni.tournamentservice.model.dto.*;
import bg.softuni.tournamentservice.repository.*;
import bg.softuni.tournamentservice.service.TournamentService;
import bg.softuni.tournamentservice.utils.Factory.TournamentDTO.TournamentDTOConverterFactory;
import bg.softuni.tournamentservice.utils.TouurnamentValidator.TournamentValidator;
import bg.softuni.userservice.models.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private  TournamentValidator tournamentValidator;
    private final TournamentDTOConverterFactory tournamentDTOConverterFactory;


    public TournamentServiceImpl(TournamentRepository tournamentRepository,
                                 TeamRepository teamRepository,
                                 TournamentValidator tournamentValidator,
                                 TournamentDTOConverterFactory tournamentDTOConverterFactory) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.tournamentValidator = tournamentValidator;
        this.tournamentDTOConverterFactory = tournamentDTOConverterFactory;

    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        return tournamentRepository.findAll().stream()
                .map(tournamentDTOConverterFactory::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getSubscribedInTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt);
        return tournamentRepository.findSubscribedTournaments(user.getId()).stream()
                .map(tournamentDTOConverterFactory::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getManagedTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt);
        return tournamentRepository.findByManagerId(user.getId()).stream()
                .map(tournamentDTOConverterFactory::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentDTO> getWatchlistTournaments(String jwt) {
        User user = tournamentValidator.authenticateUser(jwt);
        return tournamentRepository.findByFollowerId(user.getId()).stream()
                .map(tournamentDTOConverterFactory::convert)
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



    @Override
    public TournamentDTO getTournamentById(Long id, String jwt) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return tournamentDTOConverterFactory.convert(tournament);
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
