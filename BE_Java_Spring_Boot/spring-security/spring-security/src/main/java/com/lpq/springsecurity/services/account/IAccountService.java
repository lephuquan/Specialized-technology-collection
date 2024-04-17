package com.lpq.springsecurity.services.account;

import com.lpq.springsecurity.payloads.request.CreateAccountRequest;
import com.lpq.springsecurity.payloads.response.CreateAccountResponse;

public interface IAccountService {

    CreateAccountResponse createAccount(CreateAccountRequest request);
}
