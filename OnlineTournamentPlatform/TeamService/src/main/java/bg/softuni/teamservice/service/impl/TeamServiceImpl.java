package bg.softuni.teamservice.service.impl;

import bg.softuni.userservice.repository.ConsumerRepository;

public class TeamServiceImpl {
private final ConsumerRepository consumerRepository;

    public TeamServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }
}
