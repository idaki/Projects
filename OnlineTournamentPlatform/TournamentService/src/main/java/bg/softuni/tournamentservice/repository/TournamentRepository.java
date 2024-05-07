package bg.softuni.tournamentservice.repository;

import bg.softuni.tournamentservice.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament findByName(String tournament1);
}