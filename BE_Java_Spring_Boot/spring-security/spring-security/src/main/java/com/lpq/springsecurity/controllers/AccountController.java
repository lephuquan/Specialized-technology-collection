package com.lpq.springsecurity.controllers;


import com.lpq.springsecurity.payloads.request.CreateAccountRequest;
import com.lpq.springsecurity.payloads.response.CreateAccountResponse;
import com.lpq.springsecurity.services.account.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/public")
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService; // final

    @PostMapping("/account")
    public ResponseEntity<CreateAccountResponse> createAccount(@Validated @RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
