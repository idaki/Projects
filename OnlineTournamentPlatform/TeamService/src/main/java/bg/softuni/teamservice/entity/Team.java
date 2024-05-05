package bg.softuni.teamservice.entity;

import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "team")
public class Team  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "team_size")
    private int capacity;

    @OneToMany()
    private Set<User> users = new HashSet<>();

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

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
