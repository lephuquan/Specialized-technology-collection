package com.lpq.springsecurity.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpq.springsecurity.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint { // AuthenticationEntryPoint -  xử lý các yêu cầu không được xác thực hoặc không có quyền truy cập.

    private final ObjectMapper objectMapper;// dung cho chuyển đổi đối tượng Java thành dạng JSON để gửi lại trong phản hồi HTTP

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {//xác định cách xử lý các yêu cầu không được xác thực.
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {//  được gọi khi có một yêu cầu không được xác thực
        response.setContentType("application/json;charset=UTF-8");//  cài đặt kiểu và ký tự của phản hồi thành JSON.
        response.setStatus(403);
        response.getWriter().write(// sử dụng đối tượng ObjectMapper để chuyển đổi một đối tượng ErrorMessage thành chuỗi JSON và ghi chuỗi này vào phản hồi
                objectMapper.writeValueAsString(ErrorMessage.builder()
                        .status(HttpStatus.FORBIDDEN)
                        .message("Access Denied")
                        .code(403)
                        .timestamp(LocalDateTime.now())
                        .build())
        );
    }
}
