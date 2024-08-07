package bg.softuni.tournamentservice.model.dto;




import java.util.Date;




public class TournamentDTO {
    private Long id;
    private String name;
    private String game;
    private String description;
    private String url;
    private String category;
    private String summary;
    private Date startDate;
    private Date endDate;
    private int numberOfTeams;
    private int teamSize;

    public Long getId() {
        return id;
    }

    public TournamentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TournamentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getGame() {
        return game;
    }

    public TournamentDTO setGame(String game) {
        this.game = game;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TournamentDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TournamentDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public TournamentDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public TournamentDTO setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public TournamentDTO setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public TournamentDTO setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public TournamentDTO setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
        return this;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public TournamentDTO setTeamSize(int teamSize) {
        this.teamSize = teamSize;
        return this;
    }
}

