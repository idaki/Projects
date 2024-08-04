package bg.softuni.tournamentservice.model.ExportDto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TeamExportDTO {
    private final Long id;
    private final String teamName;
    private final List<String> members;
    private final List<Long> userIds;
}