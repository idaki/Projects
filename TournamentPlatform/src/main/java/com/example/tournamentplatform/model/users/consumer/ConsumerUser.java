package com.example.tournamentplatform.model.users.consumer;


import com.example.tournamentplatform.model.baseEntity.BaseEntity;
import com.example.tournamentplatform.model.teams.Team;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "consumer_users")
public class ConsumerUser extends BaseEntity {
    private String username;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "consumer_user_id_team_id",
            joinColumns = @JoinColumn(name = "consumer_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;






}