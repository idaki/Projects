package com.example.tournamentplatform.model.users.business;

import com.example.tournamentplatform.model.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "organization")
public class Organization extends BaseEntity {

    @Column
    private String username;
    @Column
    private String email;

    @OneToMany(mappedBy = "organization")
    private List<OrganizationMember> users;

}
