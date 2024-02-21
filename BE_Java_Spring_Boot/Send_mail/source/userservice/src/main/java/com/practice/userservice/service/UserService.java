package com.practice.userservice.service;

import com.practice.userservice.domain.User;

public interface UserService {

    User saveUser(User user);
    Boolean verifyToken(String token);
}
