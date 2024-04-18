package bg.softuni.usermodule.entity.consumer;

public interface Consumer {
    // Tournament Participation Methods
    void signUpForTournament(String tournamentId);
    void joinTeam(String teamId);
    void leaveTeam(String teamId);
    void cancelTournamentRegistration(String tournamentId);

    // Other Consumer-specific methods
    void viewUpcomingTournaments();
    void viewRegisteredTournaments();
    void viewJoinedTeams();
    void browseAvailableTeams();
    void searchTournamentsByName(String name);
    void viewTeamDetails(String teamId);
    void viewTournamentDetails(String tournamentId);
}
