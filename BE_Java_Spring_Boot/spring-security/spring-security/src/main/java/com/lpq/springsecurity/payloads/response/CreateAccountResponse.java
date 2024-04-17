package com.lpq.springsecurity.payloads.response;

import com.lpq.springsecurity.entities.Roles;
import com.lpq.springsecurity.entities.Token;
import com.lpq.springsecurity.entities.enums.AccountStatus;
import com.lpq.springsecurity.entities.enums.ERole;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateAccountResponse {

    private Long userId;

    private String username;

    private String email;

    private String password;

    private String fullName = null;

    private AccountStatus status;

    private Set<Token> tokens;

    private Boolean firstLogin = Boolean.TRUE;

    private Set<ERole> roles = new HashSet<>();

    public void setRolesAccountResponse(Set<Roles> rolesEntities) {
        if (rolesEntities != null) {
            this.roles = rolesEntities.stream()
                    .map(Roles::getRoleName)
                    .collect(Collectors.toSet());
        }
    }
}
