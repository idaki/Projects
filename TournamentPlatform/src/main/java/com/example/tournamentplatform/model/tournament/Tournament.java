package com.example.tournamentplatform.model.tournament;

import com.example.tournamentplatform.model.baseEntity.BaseEntity;
import com.example.tournamentplatform.model.users.business.OrganizationMember;
import com.example.tournamentplatform.model.users.consumer.ConsumerUser;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="tournaments")
public class Tournament extends BaseEntity {
    @Column
    private String name;
    @Column
    private String capacity;

    @ManyToMany
    @JoinTable(
            name = "tournament_id_team_id",
            joinColumns = @JoinColumn(name = "consumer_user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<ConsumerUser> players;




}
