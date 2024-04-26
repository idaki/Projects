package bg.softuni.userservice.repository;



import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,Long> {
    Optional<Consumer> findById(long id);

    Optional<Consumer> findByEmail(java.lang.String email);

    Optional<Consumer> findByUsername(String username);
}
