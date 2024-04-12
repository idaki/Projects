package com.example.tournamentplatform.model.users.tournament;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private boolean individual; // Indicates if the tournament is individual or team-based

    @OneToMany(mappedBy = "tournament")
    private List<Team> teams = new ArrayList<>();

    // Other tournament properties, constructors, getters, and setters
}