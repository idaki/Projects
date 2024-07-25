package bg.softuni.tournamentservice.service;


import bg.softuni.tournamentservice.model.viewDto.TeamDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamService {
    @Transactional
    void addPlayer();

    void removePlayer();

    List<TeamDTO> getMyTeams(String jwt);
}
