package bg.softuni.tournamentservice;

import bg.softuni.gameservice.model.Game;
import jakarta.persistence.*;

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

    @ElementCollection
    @CollectionTable(name = "tournament_teams", joinColumns = @JoinColumn(name = "tournament_id"))
    @Column(name = "team_id")
    private Set<Long> teamIds;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
