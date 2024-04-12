package com.example.tournamentplatform.model.users.consumerusers;

import com.example.tournamentplatform.model.users.tournament.Tournament;
import com.example.tournamentplatform.model.users.User;
import com.example.tournamentplatform.model.users.baseentities.BaseUserEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consumer_users")
public class ConsumerUser extends BaseUserEntity implements User {
    private String username;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_tournaments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournaments = new ArrayList<>();

    public ConsumerUser(String username, String email) {
        super();
        this.username = username;
        this.email = email;
    }

    // Getters and setters
}