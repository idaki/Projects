package bg.softuni.gamemodule.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<Asset> assets = new HashSet<>();

    // getters, setters, and other properties
}
