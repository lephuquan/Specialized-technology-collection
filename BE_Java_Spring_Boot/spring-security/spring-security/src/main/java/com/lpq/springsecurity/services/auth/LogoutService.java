package com.lpq.springsecurity.services.auth;

import com.lpq.springsecurity.entities.Token;
import com.lpq.springsecurity.repositories.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final ITokenRepository tokenRepository;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {//  Spring Security không cung cấp thông tin xác thực (authentication) trong quá trình logout, do đó bạn nhận được giá trị null.

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
