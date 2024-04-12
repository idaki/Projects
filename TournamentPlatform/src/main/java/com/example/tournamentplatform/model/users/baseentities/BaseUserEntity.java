package com.example.tournamentplatform.model.users.baseentities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    public String getUUID() {
        return uuid;
    }
}
