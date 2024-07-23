package bg.softuni.locationservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name="regions")
public class Region {

    @Id
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "region")
    private List<Country> countries;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
