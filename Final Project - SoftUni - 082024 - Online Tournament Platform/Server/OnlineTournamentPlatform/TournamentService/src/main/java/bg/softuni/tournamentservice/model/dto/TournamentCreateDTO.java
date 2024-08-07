package bg.softuni.tournamentservice.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class TournamentCreateDTO {


    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Game is mandatory")
    private String game;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Summary is mandatory")
    private String summary;

    @NotNull(message = "Start date is mandatory")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private Date startDate;

    @NotNull(message = "End date is mandatory")
    @Future(message = "End date must be in the future")
    private Date endDate;

    @Min(value = 1, message = "Number of teams must be at least 1")
    @Max(value = 100, message = "Number of teams must be at most 100")
    private int numberOfTeams;

    @Min(value = 1, message = "Team size must be at least 1")
    private int teamSize;
}
