package bg.softuni.tournamentservice.repository;

import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.userservice.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByManagerId(Long id);

    @Query("SELECT t FROM Tournament t JOIN t.followers f WHERE f.id = :userId")
    List<Tournament> findByFollowerId(@Param("userId") Long userId);

    Optional<Tournament> findByGameAndManagerAndName(Game game, User user, String name);
}
