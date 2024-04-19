package bg.softuni.tournamentmodule.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<Match> matches = new HashSet<>();

    // getters, setters, and other properties
}
