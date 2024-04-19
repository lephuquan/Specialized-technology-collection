package com.lpq.springsecurity.services.auth;


import com.lpq.springsecurity.payloads.request.LoginRequest;
import com.lpq.springsecurity.payloads.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest request);
}