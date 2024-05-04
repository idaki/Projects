package bg.softuni.tournamentservice.model;

import bg.softuni.gameservice.model.Game;
import bg.softuni.teamservice.entity.TeamEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tournaments_tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne
 private Game game;



    @OneToMany
    private Set<TeamEntity> teams;

    public Tournament() {
this.teams = new HashSet<>();
    }

    public Tournament(String name, Game game, Set<TeamEntity> teams) {
        this.name = name;
        this.game = game;
        this.teams = teams;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tournament setName(String name) {
        this.name = name;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
