package bg.softuni.tournamentservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TeamExportDTO {
    private  Long id;
    private  String name;
    private List<String> members;
    private  List<Long> userIds;

    public TeamExportDTO(Long id, String name, List<String> members, List<Long> userIds) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.userIds = userIds;
    }
}
