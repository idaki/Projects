package bg.softuni.usermodule.entity.business.employee;

public interface Employee {
    void createTournament();
    void updateTournament();
    void deleteTournament();
    void assignTeamToTournament();
    void generateTournamentSchedule();
    void publishTournamentResults();
    void manageTournamentParticipants();
    void viewTournamentDetails();
    void approveTournamentRegistrations();
    void rejectTournamentRegistrations();

    // Other Employee-specific methods
    void manageTeam();
    void scheduleMeeting();
    void submitExpenseReport();
    void viewPerformanceReviews();
    void requestTimeOff();
    void viewCompanyAnnouncements();
    void accessTrainingMaterials();
    void viewPayrollInformation();
}
