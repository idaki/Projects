package bg.softuni.authenticationservice.service.impl;

import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.consumer.PasswordConsumer;
import bg.softuni.userservice.repository.ConsumerRepository;
import bg.softuni.userservice.repository.PasswordConsumerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final ConsumerRepository consumerRepository;
    private final PasswordConsumerRepository passwordConsumerRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(ConsumerRepository consumerRepository, PasswordConsumerRepository passwordConsumerRepository, PasswordEncoder passwordEncoder) {
        this.consumerRepository = consumerRepository;
        this.passwordConsumerRepository = passwordConsumerRepository;
        this.passwordEncoder = passwordEncoder;


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Consumer> consumerOptional = consumerRepository.findByUsername(username);

        if (consumerOptional.isEmpty()) {
            throw new UsernameNotFoundException("Consumer not found");
        }

        Consumer consumer = consumerOptional.get();
        Optional<PasswordConsumer> passwordConsumerOptional = passwordConsumerRepository.findByUser(consumer);

        if (passwordConsumerOptional.isEmpty()) {
            throw new UsernameNotFoundException("Password not found for consumer");
        }

        PasswordConsumer passwordConsumer = passwordConsumerOptional.get();
        return new org.springframework.security.core.userdetails.User(
                consumer.getUsername(),
                passwordConsumer.getHashedPassword(),
                new ArrayList<>()
        );
    }

@Override
    // Method to save a new password for an employee
       public void savePassword(Consumer cosnumer, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
       PasswordConsumer  password = new PasswordConsumer();
        password.setHashedPassword(hashedPassword);
        password.setUser(cosnumer);
        this.passwordConsumerRepository.saveAndFlush(password);
    }


    @Override
    public void importPasswords() {
        for (long i = 1; i <=this.consumerRepository.count() ; i++) {
            Consumer consumer =this.consumerRepository.findById(i).get();
            String password = consumer.getUsername();
            this.savePassword(consumer,password);
        }
    }
}
