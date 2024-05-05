package bg.softuni.teamservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.teamservice.entity.Team;
import bg.softuni.teamservice.repository.TeamRepository;
import bg.softuni.teamservice.service.TeamService;


import bg.softuni.userservice.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl extends CrudServiceImpl<Team,Long> implements TeamService {


    private final ConsumerRepository consumerRepository;
    private final TeamRepository teamRepository;
@Autowired
    public TeamServiceImpl(ConsumerRepository consumerRepository, TeamRepository teamRepository) {
    super(teamRepository);
    this.consumerRepository = consumerRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    @Transactional
    public void addPlayer() {
//        TeamEntity team1 = new TeamEntity("Team1", 3);

//
//        for (long i = 1; i <= 3; i++) {
//            Optional<Consumer> consumer = this.consumerRepository.findById(i);
//
//            if (consumer.isPresent()) {
//                long consumerId = consumer.get().getId();
//                team1.getUsersPlatformIds().add(consumerId);
//                System.out.printf("Consumer %s added to team %s\n", consumerId, team1.getName());
//
//            } else {
//                System.out.println("Consumer not found");
//            }
        }

    @Override
    public void removePlayer() {

    }


//        this.teamRepository.saveAndFlush(team1);
    }



