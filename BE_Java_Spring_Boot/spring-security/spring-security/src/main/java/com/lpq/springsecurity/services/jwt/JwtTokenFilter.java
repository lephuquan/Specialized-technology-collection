package com.lpq.springsecurity.services.jwt;

import com.lpq.springsecurity.repositories.ITokenRepository;
import com.lpq.springsecurity.services.user.UserDetailsServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {// ke thua lop OncePerRequestFilter -> goi 1 lan kho co request den

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // release for check valid or get info from token

    @Autowired
    private UserDetailsServiceImpl customUserDetailService; // get user info from database

    @Autowired
    private ITokenRepository tokenRepository; // use query entity

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = Strings.EMPTY;
        try {
            jwt = parseJwt(request); // get token from request

            Boolean isValidToken = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);// check valid token

            if (jwt != null && jwtTokenUtil.validateAccessToken(jwt) && isValidToken) { // if jwt has not expired
                String email = jwtTokenUtil.getEmailFromJwtToken(jwt); // use jwtTokenUtil to get email
                UserDetails userDetail = customUserDetailService.loadUserByUsername(email); // accuracy and get user info by customUserDetailService

                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        userDetail,
                        null,
                        userDetail.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // set user info into contextHolder

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception ex) {
            tokenRepository.deleteByToken(jwt);
            logger.error("Cannot set user authentication: {}", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) { // spit jwt from token if exist
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
