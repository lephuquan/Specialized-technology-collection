package com.lpq.springsecurity.services.auth;

import com.lpq.springsecurity.entities.Token;
import com.lpq.springsecurity.entities.Users;
import com.lpq.springsecurity.payloads.request.LoginRequest;
import com.lpq.springsecurity.payloads.response.LoginResponse;
import com.lpq.springsecurity.repositories.IAccountRepository;
import com.lpq.springsecurity.repositories.ITokenRepository;
import com.lpq.springsecurity.services.jwt.JwtTokenUtil;
import com.lpq.springsecurity.services.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IAccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final ITokenRepository tokenRepository;

    private final MessageSource messageSource;

    @Override
    public LoginResponse login(LoginRequest request) {

        Optional<Users> usersOptional = accountRepository.findAccountByEmail(request.getEmail());

        if (!usersOptional.isPresent()) {
            // Throw exception or handle the case when user is not found
            throw new UsernameNotFoundException("User with email " + request.getEmail() + " not found");
        }

        Users user = usersOptional.get();

        // When authentication successful save authenticate in HttpSessionSecurityContextRepository
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtTokenUtil.generateAccessToken(authentication);

            // Delete old tokens and save new token
            tokenRepository.deleteByUsers(user);
            saveToken(accessToken, user);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Collection<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

            return new LoginResponse(
                    userDetails.getEmail(),
                    userDetails.getUsername(),
                    roles,
                    accessToken,
                    userDetails.isFirstLogin());
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationException(
                    messageSource.getMessage("api.account.wrong.password-or-email", null,
                            e.getMessage(), LocaleContextHolder.getLocale()
                    )
            ) {
            };
        }
    }



    private void saveToken(String accessToken, Users users) {
        Token token = new Token();
        token.setToken(accessToken);
        token.setUsers(users);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

}
