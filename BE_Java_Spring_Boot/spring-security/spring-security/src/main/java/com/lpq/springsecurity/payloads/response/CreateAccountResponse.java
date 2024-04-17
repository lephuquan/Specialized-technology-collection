package com.lpq.springsecurity.payloads.response;

import com.lpq.springsecurity.entities.Roles;
import com.lpq.springsecurity.entities.Token;
import com.lpq.springsecurity.entities.enums.AccountStatus;
import com.lpq.springsecurity.entities.enums.ERole;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CreateAccountResponse {

    private Long userId;

    private String username;

    private String email;

    private String password;

    private String fullName;

    private AccountStatus status;

    private Set<Token> tokens;

    private Boolean firstLogin;

    private Set<ERole> roles = new HashSet<>();

    public void setRolesAccountResponse(Set<Roles> rolesEntities) {
        if (rolesEntities != null) {
            this.roles = rolesEntities.stream()
                    .map(Roles::getRoleName)
                    .collect(Collectors.toSet());
        }
    }
}
