package bg.softuni.tournamentservice.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MemberDTO {
    String username;
    String imageUrl;
}