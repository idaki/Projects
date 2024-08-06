package bg.softuni.tournamentservice.model.ExportDto;

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
