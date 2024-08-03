package bg.softuni.tournamentservice.repository;


import bg.softuni.tournamentservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


    Optional<Game> findByName(String game);
}
