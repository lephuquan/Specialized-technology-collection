package com.lpq.springsecurity.controllers;


import com.lpq.springsecurity.payloads.request.CreateAccountRequest;
import com.lpq.springsecurity.payloads.response.CreateAccountResponse;
import com.lpq.springsecurity.services.account.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/public")// should be '/api/admin' cause only admin can create account
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService; // final

    @PostMapping("/account")
    public ResponseEntity<CreateAccountResponse> createAccount(@Validated @RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
