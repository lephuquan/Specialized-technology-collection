package com.lpq.springsecurity.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String email;

    private String username;

    private Collection<String> role;

    private String jwt;

    private boolean firstLogin;

}
