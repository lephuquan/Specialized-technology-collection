package com.ldcc.esg.api.services.auth;

import com.ldcc.esg.api.entities.AccountLoginHistory;
import com.ldcc.esg.api.entities.Token;
import com.ldcc.esg.api.entities.enums.ActionContent;
import com.ldcc.esg.api.repositories.IAccountLoginHistoryRepository;
import com.ldcc.esg.api.repositories.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final ITokenRepository tokenRepository;

    private final IAccountLoginHistoryRepository accountLoginHistoryRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String userAgent = request.getHeader("User-Agent");

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authHeader.substring(7);

        Optional<Token> storedToken = tokenRepository.findByToken(jwt);
        if(storedToken.isPresent()) {
            storedToken.get().setExpired(true);
            storedToken.get().setRevoked(true);

            saveAccountLogoutHistory(storedToken.get(), request, userAgent);
            tokenRepository.save(storedToken.get());
            SecurityContextHolder.clearContext();
        }

    }

    public void saveAccountLogoutHistory(Token storedToken,HttpServletRequest request, String userAgent){
        AccountLoginHistory loginHistory = AccountLoginHistory.builder()
                .actionContent(ActionContent.LOGOUT)
                .activityTimeline(LocalDateTime.now())
                .accessIp(request.getRemoteAddr())
                .browserInfo(userAgent)
                .account(storedToken.getAccount())
                .build();
        accountLoginHistoryRepository.save(loginHistory);
    }
}
