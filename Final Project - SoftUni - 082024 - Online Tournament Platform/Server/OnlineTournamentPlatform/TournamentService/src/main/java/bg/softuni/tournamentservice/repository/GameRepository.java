package bg.softuni.tournamentservice.repository;


import bg.softuni.tournamentservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
   Game getByTitle(String title);
}