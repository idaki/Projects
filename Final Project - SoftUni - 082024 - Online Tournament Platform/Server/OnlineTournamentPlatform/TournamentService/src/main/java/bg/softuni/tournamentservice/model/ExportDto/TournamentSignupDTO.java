package bg.softuni.tournamentservice.model.ExportDto;


public class TournamentSignupDTO {
    private Long tournamentId;
    private String teamName;

    // Getters and Setters

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
