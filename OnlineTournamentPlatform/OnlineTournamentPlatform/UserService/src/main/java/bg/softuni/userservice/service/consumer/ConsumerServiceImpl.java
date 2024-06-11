package bg.softuni.userservice.service.consumer;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.repository.ConsumerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl extends CrudServiceImpl<Consumer, Long> implements ConsumerService {



    private final ConsumerRepository consumerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

@Autowired
    public ConsumerServiceImpl(ConsumerRepository consumerRepository, BCryptPasswordEncoder passwordEncoder) {
        super(consumerRepository);
        this.consumerRepository = consumerRepository;
        this.passwordEncoder = passwordEncoder;
    }

@Override
    public Consumer registerConsumer(String username, String password, String email) {
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(passwordEncoder.encode(password));
        consumer.setEmail(email);
        return save(consumer);
    }
}
