package com.lpq.springsecurity.controllers;

import com.lpq.springsecurity.payloads.request.LoginRequest;
import com.lpq.springsecurity.payloads.response.LoginResponse;
import com.lpq.springsecurity.services.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest authRequest
    ) {
        return ResponseEntity.ok(iAuthService.login(authRequest));
    }
}
