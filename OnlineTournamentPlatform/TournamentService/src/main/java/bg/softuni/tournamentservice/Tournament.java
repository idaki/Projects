package bg.softuni.tournamentservice;

import bg.softuni.gameservice.Game;
import bg.softuni.teamservice.entity.Team;
import bg.softuni.teamservice.entity.TeamEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tournaments_tournament")
public class Tournament {
    @Id
    private Long id;

    @Column
    private String name;

    @OneToOne
 private Game game;
 @OneToMany
    private Set<TeamEntity> teams = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
