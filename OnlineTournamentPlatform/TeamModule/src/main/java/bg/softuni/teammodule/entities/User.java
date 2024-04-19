package bg.softuni.teammodule.entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "team_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userPlatformID;

    @ManyToMany(mappedBy = "users",targetEntity = Team.class)
    private Set<Team> teams;

}
