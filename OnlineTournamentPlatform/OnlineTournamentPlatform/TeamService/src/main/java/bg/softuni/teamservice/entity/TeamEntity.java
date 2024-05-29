package bg.softuni.teamservice.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "team_size")
    private int capacity;

    @Column(name = "tournament_id")
    private Long tournamentId;

    @ElementCollection
    @CollectionTable(name = "team_user_ids", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "user_id")
    private Set<Long> userIds;

    public TeamEntity(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.userIds = new HashSet<>();
    }

    public TeamEntity() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }
}
