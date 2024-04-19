package bg.softuni.gamemodule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "game_assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // e.g., video, trailer

    @Column(nullable = false)
    private String url; // URL to the asset

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    // getters, setters, and other properties
}
