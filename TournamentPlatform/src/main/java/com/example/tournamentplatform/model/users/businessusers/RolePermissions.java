package com.example.tournamentplatform.model.users;

import com.example.tournamentplatform.model.users.businessusers.BusinessRole;

import java.util.EnumSet;

public static EnumSet<Permission> getPermissionsForRole(BusinessRole role) {
    switch (role) {
        case ADMIN:
            return EnumSet.allOf(Permission.class);
        case ANALYTICS:
            return EnumSet.of(Permission.CREATE_TOURNAMENT);
        case MANAGER:
            return EnumSet.of(Permission.ADD_EMPLOYEE);
        default:
            return EnumSet.noneOf(Permission.class);
    }
}
