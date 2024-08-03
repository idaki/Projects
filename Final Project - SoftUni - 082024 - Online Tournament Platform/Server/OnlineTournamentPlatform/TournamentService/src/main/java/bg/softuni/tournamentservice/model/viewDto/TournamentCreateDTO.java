package bg.softuni.tournamentservice.model.viewDto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TournamentCreateDTO {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;     // Tournament title

    @NotNull(message = "Game title cannot be null")
    @NotBlank(message = "Game title cannot be blank")
    private String game;    // Game title

    @NotNull(message = "Category cannot be null")
    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Summary cannot be null")
    @NotBlank(message = "Summary cannot be blank")
    private String summary;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull(message = "Number of teams cannot be null")
    @Positive(message = "Number of teams must be positive")
    private int numberOfTeams;

    @NotNull(message = "Team size cannot be null")
    @Positive(message = "Team size must be positive")
    private int teamSize;
}
