package bg.softuni.tournamentservice.repository;

import bg.softuni.tournamentservice.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByManagerId(Long id);

}
