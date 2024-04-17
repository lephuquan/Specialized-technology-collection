package com.lpq.springsecurity.services.auth;

import com.lpq.springsecurity.entities.Token;
import com.lpq.springsecurity.repositories.ITokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final ITokenRepository tokenRepository;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authHeader.substring(7);

        Optional<Token> storedToken = tokenRepository.findByToken(jwt);
        if(storedToken.isPresent()) {
            storedToken.get().setExpired(true);
            storedToken.get().setRevoked(true);

            tokenRepository.save(storedToken.get());
            SecurityContextHolder.clearContext();
        }

    }
}
