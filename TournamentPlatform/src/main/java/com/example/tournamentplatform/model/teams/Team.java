package com.example.tournamentplatform.model.teams;

import com.example.tournamentplatform.model.baseEntity.BaseEntity;
import com.example.tournamentplatform.model.users.consumer.ConsumerUser;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    private String name;
    private int capacity;

    @ManyToMany(mappedBy = "teams")
    private List<ConsumerUser> players;

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


// Getters and setters
}
