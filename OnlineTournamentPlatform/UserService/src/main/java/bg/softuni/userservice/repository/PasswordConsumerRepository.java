package bg.softuni.userservice.repository;


import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.consumer.PasswordConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordConsumerRepository extends JpaRepository<PasswordConsumer, Long> {

    Optional<PasswordConsumer> findByUser(Consumer consumer);
}
