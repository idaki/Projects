package bg.softuni.gameservice;

import jakarta.persistence.*;

@Entity
    @Table(name = "assets")
    public class Asset {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(columnDefinition = "Text")
        private String description;


        @Column(name = "url_asset")
        private String url;

        @ManyToOne
        @JoinColumn(name = "game_id")
        private Game game;

        // Constructors, getters, and setters omitted for brevity
    }


