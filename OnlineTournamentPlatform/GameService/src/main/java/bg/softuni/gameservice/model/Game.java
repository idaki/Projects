package bg.softuni.gameservice.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "game")
    private Set<Asset> assets = new HashSet<>();

    // Constructors, getters, and setters omitted for brevity
}
