package bg.softuni.tournamentservice.model.viewDto;




import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
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
}

