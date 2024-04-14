package com.example.tournamentplatform.model.users.business;



import com.example.tournamentplatform.model.baseEntity.BaseEntity;

import com.example.tournamentplatform.model.tournament.Tournament;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "organization_memebers")
public class OrganizationMember extends BaseEntity {
    @Column
    private String username;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;
    @ManyToMany
    @JoinTable(
            name = "organization_tournament",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournamentList;


}
