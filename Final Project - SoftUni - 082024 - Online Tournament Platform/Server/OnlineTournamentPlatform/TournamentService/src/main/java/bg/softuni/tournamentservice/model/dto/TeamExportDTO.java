package bg.softuni.tournamentservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TeamExportDTO {
    private final Long id;
    private final String name;
    private final List<String> members;
    private final List<Long> userIds;
}
