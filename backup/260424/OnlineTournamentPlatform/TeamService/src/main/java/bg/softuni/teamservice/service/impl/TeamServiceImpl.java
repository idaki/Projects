package bg.softuni.teamservice.service.impl;

import bg.softuni.teamservice.entity.Team;
import bg.softuni.teamservice.repository.TeamRepository;
import bg.softuni.teamservice.service.TeamService;

import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {


    private final ConsumerRepository consumerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(ConsumerRepository consumerRepository, TeamRepository teamRepository) {
        this.consumerRepository = consumerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public void addPlayer() {
        Team team1 = new Team("Team1", 3);


        for (long i = 1; i <= 3; i++) {
            Optional<Consumer> consumer = this.consumerRepository.findById(i);

            if (consumer.isPresent()) {
                long consumerId = consumer.get().getId();
                team1.getUsersPlatformIds().add(consumerId);
                System.out.printf("Consumer %s added to team %s\n", consumerId, team1.getName());

            } else {
                System.out.println("Consumer not found");
            }
        }


        this.teamRepository.saveAndFlush(team1);
    }

    @Override
    public void removePlayer() {
        // Implementation for removing a player from a team
    }
}
