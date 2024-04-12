package com.example.tournamentplatform.model.users.businessusers;

import com.example.tournamentplatform.model.users.tournament.Tournament;
import com.example.tournamentplatform.model.users.User;
import com.example.tournamentplatform.model.users.baseentities.BaseUserEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends BaseUserEntity implements User {
    private String username;
    private String email;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private BusinessUser employer;

    @ManyToMany
    @JoinTable(
            name = "user_tournaments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournaments = new ArrayList<>();

    public Employee(String username, String email, BusinessUser employer) {
        super();
        this.username = username;
        this.email = email;
        this.employer = employer;
    }

    // Getters and setters
}