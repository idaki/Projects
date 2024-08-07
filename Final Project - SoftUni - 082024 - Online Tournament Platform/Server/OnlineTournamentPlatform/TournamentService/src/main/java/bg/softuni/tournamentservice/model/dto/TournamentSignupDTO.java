package bg.softuni.tournamentservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentSignupDTO {
    private Long tournamentId;
    private String teamName;


}
