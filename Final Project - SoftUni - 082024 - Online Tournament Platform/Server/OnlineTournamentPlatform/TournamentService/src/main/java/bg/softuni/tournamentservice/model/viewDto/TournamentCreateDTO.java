package bg.softuni.tournamentservice.model.viewDto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class TournamentCreateDTO {


    private String name;     // Tournament title


    private String game;    // Game title


    private String category;


    private String summary;


    private Date startDate;


    private Date endDate;


    private int numberOfTeams;


    private int teamSize;
}
