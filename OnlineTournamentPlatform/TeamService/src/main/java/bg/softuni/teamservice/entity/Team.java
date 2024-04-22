package bg.softuni.teamservice.entity;

import jakarta.persistence.*;

@Entity
@Table (name="team_service_team")
public class Team {
    @Id
    private long id;
    @Column
    private String name;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
