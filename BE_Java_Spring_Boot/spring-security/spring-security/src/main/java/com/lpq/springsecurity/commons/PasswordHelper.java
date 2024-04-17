package com.lpq.springsecurity.commons;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHelper {
    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean decode(String rawPassword, String password) {
        return new BCryptPasswordEncoder().matches(rawPassword, password);
    }
}
