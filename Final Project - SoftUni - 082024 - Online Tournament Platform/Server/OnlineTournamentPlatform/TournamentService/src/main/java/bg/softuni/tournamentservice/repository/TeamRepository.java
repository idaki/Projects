package bg.softuni.tournamentservice.repository;


import bg.softuni.tournamentservice.model.Team;

import bg.softuni.userservice.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {


    List<Team> getAllByUsers(User user);

    List<Team> findByTournamentId(Long tournamentId);
}