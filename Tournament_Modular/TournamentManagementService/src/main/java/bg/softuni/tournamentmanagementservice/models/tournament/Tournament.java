package bg.softuni.tournamentmanagementservice.models.tournament;


import bg.softuni.tournamentmanagementservice.models.baseEntity.BaseEntity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="tournaments")
public class Tournament extends BaseEntity {
    @Column
    private String name;
    @Column
    private String capacity;

    @ManyToMany
    @JoinTable(
            name = "tournament_id_team_id",
            joinColumns = @JoinColumn(name = "consumer_user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<ConsumerUser> players;




}
