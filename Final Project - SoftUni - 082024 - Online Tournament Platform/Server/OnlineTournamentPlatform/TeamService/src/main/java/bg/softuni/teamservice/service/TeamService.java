package bg.softuni.teamservice.service;

import org.springframework.transaction.annotation.Transactional;

public interface TeamService {
    @Transactional
    void addPlayer();

    void removePlayer();
}
