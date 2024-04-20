package bg.softuni.teammodule.entities;


import jakarta.persistence.*;

import java.util.Set;
@Entity
@Table(name = "team_teams")

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;


 




    // getters, setters, and other properties
}
