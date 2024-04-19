package bg.softuni.usermodule.repository;

import bg.softuni.usermodule.models.entity.consumer.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,Long> {
}
