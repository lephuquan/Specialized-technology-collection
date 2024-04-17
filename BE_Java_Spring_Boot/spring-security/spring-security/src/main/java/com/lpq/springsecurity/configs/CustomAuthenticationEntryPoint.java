package com.ldcc.esg.api.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldcc.esg.api.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(
                objectMapper.writeValueAsString(ErrorMessage.builder()
                        .status(HttpStatus.FORBIDDEN)
                        .message("Access Denied")
                        .code(403)
                        .timestamp(LocalDateTime.now())
                        .build())
        );
    }
}
