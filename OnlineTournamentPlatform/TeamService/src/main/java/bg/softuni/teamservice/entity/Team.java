package bg.softuni.teamservice.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team_team_users")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "team_size")
    private int capacity;

    @ElementCollection
    @CollectionTable(name = "team_user_platform_ids", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "user_platform_id")
    private Set<Long> usersPlatformIds;

    public Team(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.usersPlatformIds = new HashSet<>();
    }

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

    public Set<Long> getUsersPlatformIds() {
        return usersPlatformIds;
    }

    public void setUsersPlatformIds(Set<Long> usersPlatformIds) {
        this.usersPlatformIds = usersPlatformIds;
    }
}
