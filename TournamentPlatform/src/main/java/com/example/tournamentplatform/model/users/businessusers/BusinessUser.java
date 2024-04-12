package com.example.tournamentplatform.model.users.businessusers;

import com.example.tournamentplatform.model.users.tournament.Tournament;
import com.example.tournamentplatform.model.users.User;
import com.example.tournamentplatform.model.users.baseentities.BaseUserEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "business_users")
public class BusinessUser extends BaseUserEntity implements User {
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private BusinessRole role;

    @OneToMany(mappedBy = "employer")
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_tournaments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournaments = new ArrayList<>();

   public BusinessUser(){

   }

    public boolean hasPermission(Permission permission) {
        return RolePermissions.getPermissionsForRole(role).contains(permission);
    }

    // Getters and setters
}
