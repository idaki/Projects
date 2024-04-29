package bg.softuni.userservice.service.consumer;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.repository.ConsumerRepository;

import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl extends CrudServiceImpl<Consumer, Long> implements ConsumerService {

private final ConsumerRepository consumerRepository;

    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        super(consumerRepository);
        this.consumerRepository = consumerRepository;
    }
}
