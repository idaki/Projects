package bg.softuni.tournamentservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TournamentSignupDTO {
    private Long tournamentId;
    private String teamName;


}
